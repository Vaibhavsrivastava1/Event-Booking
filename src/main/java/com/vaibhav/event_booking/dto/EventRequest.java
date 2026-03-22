package com.vaibhav.event_booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EventRequest {
 

    @NotBlank(message = "Event tittle is requied")
     private  String tittle ; 

     @NotBlank(message = "Description is Required")
     private String description ; 
 
     @NotBlank(message = "Event Date is required")
     private String eventDate ; 

     @Positive(message = "Seats Cannot be Negative")
     @NotNull(message = "Seats number is Requird")
     private Long totalSeats; 
     


}
