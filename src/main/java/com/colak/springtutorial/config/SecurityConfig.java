package com.colak.springtutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.HttpSessionSessionStrategy;

@Configuration
public class SecurityConfig {

    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new HttpSessionSessionStrategy();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy())
                // Other session management configurations...
                .and()
        // Other security configurations...
        ;
        return http.build();
    }
}
