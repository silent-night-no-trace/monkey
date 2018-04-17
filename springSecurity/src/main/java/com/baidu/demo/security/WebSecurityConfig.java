package com.baidu.demo.security;

import com.baidu.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

/**
 * @author liangz
 * @date 2018/4/12 16:37
 **/
@Configuration
@EnableWebSecurity//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService userDetailService;

	@Autowired
	private SessionRegistry sessionRegistry;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors().and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/druid/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/fonts/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/logout").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement()
				.maximumSessions(1)
				.sessionRegistry(sessionRegistry).and()
				.and()
				.logout()
				.invalidateHttpSession(true).clearAuthentication(true).and()
				.httpBasic().authenticationEntryPoint(new RestAuthenticationEntryPoint()).and()
				.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()).and()
				.requestCache().requestCache(new NullRequestCache())
		;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(new )
		auth.userDetailsService(userDetailService).passwordEncoder(new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				return MD5Util.encode((String)charSequence);
			}

			@Override
			public boolean matches(CharSequence charSequence, String encodedPassword) {
				return encodedPassword.equals(MD5Util.encode((String)charSequence));
			}
		});
	}

	@Bean
	public SessionRegistry getSessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}
}
