package com.baidu.demo.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * @author liangz
 * @date 2018/4/12 16:48
 * 自定义 拦截器
 **/
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		if(exception.getClass() == InsufficientAuthenticationException.class ||
				exception.getCause() instanceof AccessDeniedException) {
			response.getWriter().println(JSON.toJSONString("权限不够，拒绝访问"));
		} else if(exception.getClass() == UserLockedException.class) {
			response.getWriter().println(JSON.toJSONString("用户锁定"));
		} else {
			response.getWriter().println(JSON.toJSONString("用户名或密码不正确"));
		}

		response.getWriter().flush();

	}
}
