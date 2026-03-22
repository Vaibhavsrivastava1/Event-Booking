package com.vaibhav.event_booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.event_booking.dto.ApiResponse;
import com.vaibhav.event_booking.dto.BookingResponse;
import com.vaibhav.event_booking.dto.EventResponse;
import com.vaibhav.event_booking.entity.Event;
import com.vaibhav.event_booking.service.BookingService;
import com.vaibhav.event_booking.service.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;





@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final BookingService bookingService ;
    private final EventService eventService;

    
    @PostMapping("/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<String>> makeBooking( @PathVariable Long eventId , Authentication authentication) {

         log.info("API /booking request received for eventId: {}", eventId);
        
        String email = authentication.getName();
         String response = bookingService.bookEvent(email, eventId);
         return ResponseEntity.ok(new ApiResponse<String>( true,"successfull",response));
        
    }

    @GetMapping("/my-bookings")
    public  ResponseEntity<ApiResponse<List<BookingResponse>>>   getBookings(Authentication authentication,
                                                @RequestParam(required = false , defaultValue = "1")  int pageNumber,
                                                @RequestParam(required =  false , defaultValue = "5" )   int pageFile )
     {
         log.info("API  /  REQUEST RECIEVED FOR BOOKING LIST");

        String email = authentication.getName();
         List<BookingResponse>  response =  bookingService.getMyBookings(email, PageRequest.of(pageNumber, pageFile));
         return ResponseEntity.ok(new ApiResponse<List<BookingResponse>>(true , "Successful", response));
       
    }

    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasRole('USER')")
    public  ResponseEntity<ApiResponse<String>> deleteBooking( @PathVariable Long bookingId , Authentication authentication)
    {

         log.info("API  /  REQUEST RECIEVED FOR  DELETING  BOOKING");

        String email = authentication.getName();

         bookingService.cancelBooking(email, bookingId);
            return ResponseEntity.ok(new ApiResponse<String>( true,"successfull","Booking Cancelled Successfully "));



    }

    @GetMapping("/events")
    public  ResponseEntity<ApiResponse<List<EventResponse>>>  getEventList( @RequestParam(required = false , defaultValue = "1")  int pageNumber,
                                 @RequestParam(required =  false , defaultValue = "5" )   int pageFile   ) {

       log.info("API  /  REQUEST RECIEVED FOR EVENTS LISTS");                              

      List<EventResponse>  response =  eventService.getEvents(PageRequest.of(pageNumber, pageFile));   
      return ResponseEntity.ok(new ApiResponse<List<EventResponse>>(true , "Successful", response)); 


        
    }
    
    
    
    

}
