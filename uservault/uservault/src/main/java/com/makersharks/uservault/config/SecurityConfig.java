package com.makersharks.uservault.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user/register", "/api/user/fetch", "/api/user/updatePassword", "/api/user", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Allow unrestricted access
                        .anyRequest().authenticated() // Authenticate all other requests
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // Disable HTTP Basic authentication
                .formLogin(form -> form.disable()); // Disable form login

        return http.build();
    }
}
