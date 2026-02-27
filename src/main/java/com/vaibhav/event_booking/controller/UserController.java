package com.vaibhav.event_booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.event_booking.dto.BookingResponse;

import com.vaibhav.event_booking.service.BookingService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;






@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final BookingService bookingService ;

    
    @PostMapping("/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public String postMethodName( @PathVariable Long eventId , Authentication authentication) {
        
        String email = authentication.getName();
        return bookingService.bookEvent(email, eventId);
    }

    @GetMapping("/my-bookings")
    public List<BookingResponse> getMethodName(Authentication authentication) {

        String email = authentication.getName();
         return bookingService.getMyBookings(email);
       
    }

    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasRole('USER')")
    public String deleteBooking( @PathVariable Long bookingId , Authentication authentication)
    {
        String email = authentication.getName();

         bookingService.cancelBooking(email, bookingId);
           return "Booking canceled successfully";



    }
    
    
    

}
