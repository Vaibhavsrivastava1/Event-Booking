package com.vaibhav.event_booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
     
    @NotBlank(message = "Name Cannot be Empty")
    private String name ; 
     
    @Email(message = "Eamil is Required")
    private String email;
     
    @NotBlank(message = "Password is Required")
    private String password;


}
