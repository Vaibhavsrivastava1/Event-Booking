package com.vaibhav.event_booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.event_booking.dto.EventRequest;
import com.vaibhav.event_booking.entity.Event;

import com.vaibhav.event_booking.service.EventService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {


    private final EventService eventService ;


    @GetMapping("/")
    public String getMethodName() {
        return "HELLO ADMIN ";
    }
    @PostMapping("/event")
    @PreAuthorize("hasRole('ADMIN')")
    public String createEvent(@RequestBody  EventRequest request) {

      return  eventService.addEvent(request);

    }
    
    

}
