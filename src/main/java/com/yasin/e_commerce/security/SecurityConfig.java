package com.yasin.e_commerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http
				.csrf(t -> t.disable())
				.authorizeHttpRequests(t -> 
				t
					.anyRequest().permitAll()
				)
				.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}

}
