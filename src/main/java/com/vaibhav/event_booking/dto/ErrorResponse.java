package com.vaibhav.event_booking.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
     private boolean success;
    private String message;
    private Map<String, String> errors;
    private LocalDateTime timestamp;

}
