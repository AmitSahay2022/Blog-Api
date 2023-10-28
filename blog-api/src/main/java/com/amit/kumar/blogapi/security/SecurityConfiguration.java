package com.amit.kumar.blogapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {
	private UserDetailsService userDetailsService;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
		security.authorizeHttpRequests(auth->{
			auth.requestMatchers(HttpMethod.POST,"/api/users").permitAll();
			auth.requestMatchers(HttpMethod.POST,"/api/users/login").permitAll();
			auth.requestMatchers(HttpMethod.GET,"/api/posts/**").permitAll();
			auth.anyRequest().authenticated();
		});
		security.httpBasic(Customizer.withDefaults());
		//Form based authentication is not require for RESTFul APIs
		security.formLogin(Customizer.withDefaults());
		
		//CSRF is Must to Disable for RESTFul APIs
		security.csrf(csrf->csrf.disable());
		
		return security.build();
	}
	
	//--------This bean is used to create login API--------------
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
