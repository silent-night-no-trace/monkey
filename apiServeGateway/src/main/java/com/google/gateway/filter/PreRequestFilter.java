package com.google.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.google.gateway.LocalCache;
import com.google.gateway.Token;
import com.google.gateway.properties.AppProperties;
import com.google.gateway.util.AesUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 请求过滤器
 * 
 * 对APP请求
 * 	1.以“/Api-App/”路由到APP的服务端App-Service，需要校验token
 * 	2.包含“/auth/” 的请求是需要校验token的
 * 	3.其他的请求为安全请求
 * 
 * @author liangz
 *
 */
@Slf4j
@Component
public class PreRequestFilter extends ZuulFilter {

	@Autowired
	private AppProperties appProperties;

	private String bigFile = "http://download.springsource.com/release/STS/3.9.1.RELEASE/dist/e4.7/spring-tool-suite-3.9.1.RELEASE-e4.7.1a-win32-x86_64.zip";

	private String unknown = "unknown";

	private final String dot = ",";

	private final Long ONE_DAY = (long) (24 * 60 * 60 * 1000);

	private final  String SEND_MESSAGE_REQUEST = "/vcode";

	private final  String AUTH_REQUEST = "/auth/";

	/**
	 *	pre：可以在请求被路由之前调用
	 *	route：在路由请求时候被调用
	 *	post：在route和error过滤器之后被调用
	 *	error：处理请求时发生错误时被调用
	 *	Zuul的主要请求生命周期包括“pre”，“route”和“post”等阶段。对于每个请求，都会运行具有这些类型的所有过滤器。
	 * @return String
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 优先级为0，数字越大，优先级越低
	 * @return  int
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

	/**
	 * 是否执行该过滤器
	 * @return boolean
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		long start = System.currentTimeMillis();
		RequestContext ctx = RequestContext.getCurrentContext();
		//解决中文乱码问题
		ctx.getResponse().setContentType("text/html;charset=UTF-8");
		HttpServletRequest request = ctx.getRequest();

		long now = System.currentTimeMillis();
    	
		String requestPath = request.getServletPath();


    	/*
    	 * App接口
    	 */
    	if(isApp(requestPath)) {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            } else if (ip.contains(dot)) {
                ip = ip.split(",")[0];
            }
            if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
    		log.info(String.format("ip=%s, req=%s", ip, requestPath));
    		if(!StringUtils.isEmpty(ip)) {
    			//IP黑名单检测
        		boolean hitBlackIP = LocalCache.hitBlackIP(ip);
        		if(hitBlackIP) {
            		log.error(String.format("命中IP黑名单, ip=%s, req=%s", ip, requestPath));
            		blackResponseBody(ctx);
            		return null;
        		}
        		
        		//短信请求
    			caseVcode(requestPath, ip);
    		}

    		/*
    		 * 1.安全请求检测
    		 * 不包含/auth/的请求为安全请求
    		 */
    		if(!requestPath.contains(AUTH_REQUEST)) {
    			log.info("安全请求"+requestPath);
    			return null;
    		}

			log.info("请求="+requestPath);
    		/*
    		 * 2.过滤重复请求
    		 * path+parameters+headers
    		 */
    		StringBuilder parameters = new StringBuilder();
    		Enumeration<String> parameterNames = request.getParameterNames();
    		while(parameterNames.hasMoreElements()) {
    			String pKey = parameterNames.nextElement();
    			String pValue = request.getParameter(pKey);
    			parameters.append(pKey).append("=").append(pValue).append(",");
    		}
    		StringBuilder headersStr = new StringBuilder();
    		Enumeration<String> headers = request.getHeaderNames();
    		while(headers.hasMoreElements()) {
    			String headerName = headers.nextElement();
    			if(Sets.newHashSet("cache-control").contains(headerName)) {
    				continue;
    			}
    			String headerValue = request.getHeader(headerName);
    			headersStr.append(headerName).append("=").append(headerValue).append(",");
    		}
    		String req = requestPath +"|" + parameters +"|" + headersStr.toString();
        	if(LocalCache.repeatRequest(req)) {
        		log.error("重复请求,req="+req);
        		repeatResponseBody(ctx);
        		return null;
        	}

        	/*
        	 * 3.是否登录
        	 */
    		String token = request.getParameter("token");
    		
    		//解密token
    		Token tokenOk = decryptToken(token);
    		if(tokenOk == null || tokenOk.getId() == null || tokenOk.getId() < 0) {
        		log.error("无效token");
    			unloginResponseBody(ctx);
        		return null;
    		}
			//Token有效期验证
    		Long loginTime = tokenOk.getLoginTime();
    		if(loginTime == null || (now - loginTime) >= appProperties.getTokenDay()*ONE_DAY) {
        		log.error("token已失效，请重新登录");
    			unloginResponseBody(ctx);
        		return null;
    		}
    		
    		/*
    		 * 4.检测请求重放攻击
    		 * 	客户2个特殊请求间隔不能小于5秒钟
    		 */
    		String custReq = "";
    		boolean tooSoon = false;
			custReq = tokenOk.getId() + "-" + requestPath;
            /*//客户请求
    		if(ImmutableConfig.SecondNotCanRepeatRequestUrlSet.contains(requestPath)){
    			tooSoon = LocalCache.custSecondRequestTooSoon(custReq);
			}
    		if(tooSoon) {
    			log.error(String.format("检测到请求重放攻击,req=%s", custReq));
    			repeatResponseBody(ctx);
        		return null;
    		}*/
    		
			//将userId传递给App-Service
			ctx.addZuulRequestHeader("userId", tokenOk.getId()+"");
            //当前app的版本信息
    		ctx.addZuulRequestHeader("vr", request.getHeader("vr")+"");
            //当前app的平台
			ctx.addZuulRequestHeader("pf", request.getHeader("pf")+"");

			long end = System.currentTimeMillis();
			log.info("App路由检测完毕，use="+(end-start));
    	}
    	
    	
    	
        return null;
	}

	/**
	 * 是APP请求
	 * 
	 * @param pathInfo
	 * @return boolean
	 */
	private boolean isApp(String pathInfo) {
		return pathInfo.indexOf("/Api-App/") == 0;
	}

	/**
	 * 解密Token
	 * 
	 * @param token
	 * @return userId
	 */
	private Token decryptToken(String token) {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		
		String tokenKey = appProperties.getTokenKey();
		try {
			String tokenOk = AesUtils.aesDecryptHexString(token, tokenKey);
			Token tk = JSONObject.parseObject(tokenOk, Token.class);
			return tk;
		} catch (Exception e) {
			log.error(String.format("token解密失败：token=%s", token));

			return null;
		}
	}
	

	/**
	 * 短信请求
	 * @param requestPath
	 */
	private void caseVcode(String requestPath, String ip) {
		if(!requestPath.endsWith(SEND_MESSAGE_REQUEST)) {
			return;
		}
		//IP地址6小时内发送短信超过10次，自动加入IP黑名单
		LocalCache.checkVcodeOfIp(ip);
	}

	
	/**
	 * 重复请求responseBody
	 */
	private void repeatResponseBody(RequestContext ctx) {
        ctx.setResponseStatusCode(HttpServletResponse.SC_OK);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody("{\"success\":\"false\",\"errCode\":\"E001\",\"errMsg\":\"操作频繁，请稍候再操作\"}");
            ctx.setSendZuulResponse(false);
        }
	}

	/**
	 * 未登录responseBody
	 */
	private void unloginResponseBody(RequestContext ctx) {
        ctx.setResponseStatusCode(HttpServletResponse.SC_OK);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody("{\"success\":\"false\",\"errCode\":\"E002\",\"errMsg\":\"App Not Login\"}");
            ctx.setSendZuulResponse(false);
        }
	}

	/**
	 * 黑名单请求responseBody
	 */
	private void blackResponseBody(RequestContext ctx) {
		try {
			ctx.getResponse().sendRedirect(bigFile);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
	}
}
