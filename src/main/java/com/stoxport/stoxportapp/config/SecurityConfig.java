package com.stoxport.stoxportapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/**").permitAll()  // Allow public access to authentication endpoints
                        .requestMatchers("/api/**").authenticated()  // Require authentication for user endpoints
                        .anyRequest().permitAll()  // Permit all other requests
                );

        return http.build();
    }
}


