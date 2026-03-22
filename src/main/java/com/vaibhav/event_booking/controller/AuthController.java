package com.vaibhav.event_booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.event_booking.dto.ApiResponse;
import com.vaibhav.event_booking.dto.LoginRequest;
import com.vaibhav.event_booking.dto.RegisterRequest;
import com.vaibhav.event_booking.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>>  register(  @Valid @RequestBody RegisterRequest request) {
        log.info("API / REGISTER AN USER  ");
        return ResponseEntity.ok(new ApiResponse<String>( true,"successfull",userService.register(request)) );

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login( @Valid @RequestBody LoginRequest request) {
     log.info("API / LOGIN   ");    
      return ResponseEntity.ok(new ApiResponse<String>( true,"successfull",userService.login(request)) );
   
     }
    


}
