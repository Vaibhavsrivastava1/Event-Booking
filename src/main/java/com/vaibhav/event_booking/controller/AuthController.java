package com.vaibhav.event_booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.event_booking.dto.LoginRequest;
import com.vaibhav.event_booking.dto.RegisterRequest;
import com.vaibhav.event_booking.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(  @Valid @RequestBody RegisterRequest request) {
        return userService.register(request);    
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
    return userService.login(request);
     }
    


}
