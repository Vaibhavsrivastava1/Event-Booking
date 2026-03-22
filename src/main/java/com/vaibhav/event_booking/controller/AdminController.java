package com.vaibhav.event_booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.event_booking.dto.ApiResponse;
import com.vaibhav.event_booking.dto.EventRequest;


import com.vaibhav.event_booking.service.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {


    private final EventService eventService ;
    @PostMapping("/event")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>>  createEvent( @Valid  @RequestBody  EventRequest request) {

        log.info("API / CREATE AN USER  ");
       return ResponseEntity.ok(new ApiResponse<String>( true,"successfull",eventService.addEvent(request)) );
   
    

    }


}
