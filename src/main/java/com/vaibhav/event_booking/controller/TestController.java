package com.vaibhav.event_booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/test")
public class TestController {
     
    @Autowired
    private PasswordEncoder  passWordConfig;

    @GetMapping("/encode")
public String encode() {
    return  passWordConfig.encode("admin123");
}


    @GetMapping
    public String getMethodName() {
        return "You are Authenticated";
    }
    

}
