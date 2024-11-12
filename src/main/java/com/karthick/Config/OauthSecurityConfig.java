package com.karthick.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@Order(2)
public class OauthSecurityConfig {
//	request.requestMatchers("/login").permitAll()
	@Bean
	public SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception
	{
		return http.authorizeHttpRequests(request->	{request.requestMatchers("/login").permitAll();
		                                            	request.anyRequest().authenticated();
		})
				.oauth2Login(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults())
				.build();
		
	}
}
