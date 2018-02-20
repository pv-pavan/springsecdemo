package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.context.request.RequestContextListener;

/**
 * Created by pv on 19/2/18.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true, proxyTargetClass=true)
public class MySecurityConfig  extends WebSecurityConfigurerAdapter{


	protected void configure(HttpSecurity http) throws Exception{

		http
				.csrf().disable()
				.formLogin().disable()
				.authorizeRequests()
					.antMatchers("/login").permitAll()
					.anyRequest().fullyAuthenticated();

		http
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/**/logout"))
				.deleteCookies("jsessionid")
				.invalidateHttpSession(true);

		http
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
	}

	@Bean
	public HttpSessionStrategy httpSessionStrategy(){
		CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName("jsessionid");
		strategy.setCookieSerializer(serializer);
		return strategy;
	}


	@Bean
	public RequestContextListener requestContextListener(){
		return new RequestContextListener();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher(){
		return new HttpSessionEventPublisher();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationManager());
	}


	@Bean
	public MyAuthenticationManager getAuthenticationManager(){
		MyAuthenticationManager myAuthenticationManager = new MyAuthenticationManager();
		return myAuthenticationManager;
	}
}
