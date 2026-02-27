package com.vaibhav.event_booking.security;

import java.io.IOException;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

     @Autowired
    private JWTUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            String header = request.getHeader("Authorization");

            if(header!=null  &&  header.startsWith("Bearer ")){
               
                
                String token = header.substring(7);
                Claims claim = jwtUtil.extractAllClaims(token);
                String email = claim.getSubject();
                String role = claim.get("role", String.class);

                if(email!=null){
                    UsernamePasswordAuthenticationToken authentication = 
                                                   new UsernamePasswordAuthenticationToken(
                                                    email,
                                                     null ,
                                                     List.of(new SimpleGrantedAuthority("ROLE_" + role))
                                                    );
                         SecurityContextHolder.getContext().setAuthentication(authentication);                           
                }
            }


            filterChain.doFilter(request, response);
       
        
    }

    


}
