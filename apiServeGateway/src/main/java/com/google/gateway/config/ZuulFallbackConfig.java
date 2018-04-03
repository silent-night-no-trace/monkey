package com.google.gateway.config;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * ZUUl 转发失败回调
 * @author liangz
 * @date 2018/01/30
 *  FallbackProvider  直接实现了继承ZuulFallbackProvider
 */
@Slf4j
@Component
public class ZuulFallbackConfig implements FallbackProvider {
	private static byte[] TryLater_Body = "{\"success\":\"false\",\"errCode\":\"E001\",\"errMsg\":\"请稍后重试\"}".getBytes();
    
	private static ClientHttpResponse fallbackResponse = new ClientHttpResponse() {

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.OK;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return 200;
        }

        @Override
        public String getStatusText() throws IOException {
            return "OK";
        }

        @Override
        public void close() {

        }

        @Override
        public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(TryLater_Body);
        }

        @Override
        public HttpHeaders getHeaders() {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return headers;
        }
    };
	
	@Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		if(request != null) {
			String requestPath = request.getServletPath();
	    	log.error(String.format("路由失败回调,req=%s", requestPath));
		}
    	
        return fallbackResponse;
    }

    /**
     * 重写的新的接口方法
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if(request != null) {
            String requestPath = request.getServletPath();
            log.error(String.format("路由失败回调，错误信息=%s,req=%s",cause.getMessage(), requestPath));
        }
        return fallbackResponse;
    }
}