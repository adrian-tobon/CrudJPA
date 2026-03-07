package com.curso.springboot.crud.jpa.security;

import java.util.Arrays;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.curso.springboot.crud.jpa.security.filter.JwtAuthenticationFilter;
import com.curso.springboot.crud.jpa.security.filter.JwtValidationFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	AuthenticationManager authenticationManager() {
		return authenticationConfiguration.getAuthenticationManager();
		
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(
				(authz) -> authz
				.requestMatchers(HttpMethod.GET,"/api/users").permitAll()
				//.requestMatchers(HttpMethod.POST,"/api/users").permitAll()
				.requestMatchers(HttpMethod.POST,"/api/users/register").permitAll()
				//.requestMatchers(HttpMethod.POST,"/api/users").hasRole("ADMIN")
				//.requestMatchers(HttpMethod.GET,"/api/products","/api/products/{id}").hasAnyRole("ADMIN","USER")
				//.requestMatchers(HttpMethod.POST,"/api/products").hasRole("ADMIN")
				//.requestMatchers(HttpMethod.PUT,"/api/products/{id}").hasRole("ADMIN")
				//.requestMatchers(HttpMethod.DELETE,"/api/products/{id}").hasRole("ADMIN")
				//.requestMatchers("/api/products").permitAll()
				//.requestMatchers("/api/products/**").permitAll()
				.anyRequest().authenticated())
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
				.addFilter(new JwtValidationFilter(authenticationManager()))
				.csrf(config -> config.disable())
				.cors(cors-> cors.configurationSource(corsConfigurationSource()))
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();

	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOriginPatterns(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
		config.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		config.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return (CorsConfigurationSource) source;
		
	}
	
	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> corsFilter = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		
		corsFilter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return corsFilter;
	}

}
