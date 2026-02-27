package com.vaibhav.event_booking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vaibhav.event_booking.security.JWTAuthenticationFilter;
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

         http
             .csrf(csrf-> csrf.disable())
             .authorizeHttpRequests(auth ->auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/test/**").permitAll()
                 .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/user/**").hasRole("USER")
                .anyRequest().authenticated()
             ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

         return http.build();    
    }

}
