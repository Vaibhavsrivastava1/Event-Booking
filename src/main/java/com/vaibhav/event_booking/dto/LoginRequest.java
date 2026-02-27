package com.vaibhav.event_booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

     @Email
    private String username;
     
    @NotBlank
    private String password;

    

}
