package com.google.style.configuration;

import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leon
 * @date 2019-08-02 11:15:38
 */
@Configuration
public class SessionConfiguration {

	@Bean
	public SessionDAO sessionDAO() {
		return new EnterpriseCacheSessionDAO();
	}
}
