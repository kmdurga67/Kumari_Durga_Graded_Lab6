package com.greatlearning.sra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
		
		AuthenticationManagerBuilder authManager = http.getSharedObject(AuthenticationManagerBuilder.class);
		authManager.authenticationProvider(authenticationProvider());
		
		return authManager.build();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http.authorizeRequests()
			.antMatchers("/h2-console**","/login**","/logout**")
				.permitAll()
			.antMatchers(HttpMethod.GET,"/students**")
			 .hasAnyRole("USER","ADMIN")
			.antMatchers(HttpMethod.POST,"/students**")
				.hasAnyRole("USER","ADMIN")
			.antMatchers(HttpMethod.PUT,"/students/**")
				.hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE,"/students/**")
				.hasRole("ADMIN")
			.anyRequest()
				.fullyAuthenticated()
			.and()
			.formLogin()
			.and()
			.httpBasic();
					
		return http.build();
	}
}
