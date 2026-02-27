package com.vaibhav.event_booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EventRequest {
 

    @NotBlank
     private  String tittle ; 

     @NotBlank
     private String description ; 
 
     @NotBlank
     private String eventDate ; 

     @Positive
     @NotBlank
     private Long totalSeats; 
     


}
