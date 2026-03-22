package com.vaibhav.event_booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

     @Email(message = "Email is Required")
    private String username;
     
    @NotBlank(message = "Password is required")
    private String password;

    

}
