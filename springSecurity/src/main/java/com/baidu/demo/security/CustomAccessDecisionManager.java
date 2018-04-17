package com.baidu.demo.security;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.baidu.demo.constant.Constants;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author liangz
 * @date 2018/4/12 18:10
 *  自定义 权限决策管理者
 **/
@Service
public class CustomAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		String url;
		if (Constants.AUTH_ANONYMOUS_USER.equals(authentication.getPrincipal())
				|| matchers("/images/**", request)
				|| matchers("/excel/**", request)
				|| matchers("/js/**", request)
				|| matchers("/css/**", request)
				|| matchers("/fonts/**", request)
				|| matchers("/", request)
				|| matchers("/index.html", request)
				|| matchers("/favicon.ico", request)
				|| matchers("/login", request)
				|| matchers("/logout", request)){
			return;

		}else {
			for (GrantedAuthority ga:authentication.getAuthorities()){
				if (ga instanceof CustomGrantedAuthority) {
					CustomGrantedAuthority grantedAuthority = (CustomGrantedAuthority) ga;
					url = grantedAuthority.getPermissionUrl();
					System.out.println("允许通过的接口："+url);
					if (matchers(url, request)) {
						System.out.println("验证通过的接口："+url);
						return;
					}
				}

			}
		}
		//权限不够 抛出异常
		throw new AccessDeniedException("");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}


	private boolean matchers(String url, HttpServletRequest request) {
		AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
		if (matcher.matches(request)) {
			return true;
		}
		return false;
	}

}
