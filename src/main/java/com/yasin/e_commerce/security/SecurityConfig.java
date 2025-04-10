package com.yasin.e_commerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yasin.e_commerce.core.EndsWithRequestMatcher;
import com.yasin.e_commerce.entities.Role;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	private final AuthenticationProvider authenticationProvider;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
    public SecurityConfig(AuthenticationProvider authenticationProvider,
			JwtAuthenticationFilter jwtAuthenticationFilter) {
		super();
		this.authenticationProvider = authenticationProvider;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}



	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http
				.csrf(t -> t.disable())
				.authorizeHttpRequests(t -> 
				t
					.requestMatchers("/api/auth/**").permitAll()
					.anyRequest().permitAll()
					/*
					.requestMatchers(new EndsWithRequestMatcher("update"))
					.hasAnyRole(
							Role.ROLE_ADMIN.getValue(),
							Role.ROLE_USER.getValue()
							)
					.requestMatchers(new EndsWithRequestMatcher("getall"))
					.hasAnyRole(
							Role.ROLE_ADMIN.getValue(),
							Role.ROLE_USER.getValue()
							)

					.requestMatchers(new EndsWithRequestMatcher("add"))
					.hasAnyRole(
							Role.ROLE_USER.getValue(),
							Role.ROLE_ADMIN.getValue()
							)
					*/
					
				)
				.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, 
						UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	

}
