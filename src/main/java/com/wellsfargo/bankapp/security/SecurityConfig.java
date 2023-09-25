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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	http.headers().addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers","Authorization"));
//        http.csrf(csrf->csrf.disable())
//        		.cors(cors->cors.disable())
//        		.authorizeHttpRequests().antMatchers("/api/v1/customer/login").permitAll().anyRequest().authenticated().and()
//        		.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//        		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//        
//        return http.build();
    	CorsConfiguration corsConfiguration = new CorsConfiguration();
    	corsConfiguration.setAllowedHeaders(List.of("authorization", "Cache-Control", "Content-Type"));
    	corsConfiguration.setAllowedOrigins(List.of("*"));
    	corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    	corsConfiguration.setAllowCredentials(true);
    	corsConfiguration.setExposedHeaders(List.of("authorization"));
    	
    	http
    			.csrf(csrf->csrf.disable())
    			.cors(cors->cors.disable())
    			.authorizeRequests()
    			.antMatchers("/api/v1/customer/login").permitAll()
    			.anyRequest().authenticated().and()
    			.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
    			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    	 http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    	 return http.build();
    	
    }

}