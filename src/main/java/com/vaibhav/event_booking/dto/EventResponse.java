package com.vaibhav.event_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

    private String tittle ;

    private String Description ; 

    private long availableSeats ; 

}
