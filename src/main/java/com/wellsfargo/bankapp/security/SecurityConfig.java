package com.wellsfargo.bankapp.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.servlet.HttpSecurityDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	http
    			.csrf(csrf->csrf.disable())
    			.cors().and()
    			.authorizeRequests()
    			.antMatchers("/api/v1/customer/login", "/api/v1/customer/register", "/api/v1/admin/*", "/api/v1/admin/*/*", "/api/v1/admin/*/*/*").permitAll()
    			.anyRequest().authenticated().and()
    			.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
    			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    	 http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    	 return http.build();
    	
    }
    
    
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration corsConfiguration = new CorsConfiguration();
    	corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
    	corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
    	corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    	corsConfiguration.setAllowCredentials(true);
    	corsConfiguration.setExposedHeaders(List.of("Authorization"));
    	
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", corsConfiguration);
    	return source;
	}

}
