package com.vaibhav.event_booking.service;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaibhav.event_booking.dto.LoginRequest;
import com.vaibhav.event_booking.dto.RegisterRequest;

import com.vaibhav.event_booking.entity.Role;
import com.vaibhav.event_booking.entity.User;
import com.vaibhav.event_booking.repository.UserRepository;
import com.vaibhav.event_booking.security.JWTUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public String register( RegisterRequest request){

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new  RuntimeException("Email already exist");
        }

        User user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();

        userRepository.save(user);
        
        return "Registered Successfully";

    }

    public String login(LoginRequest request){
        User user = userRepository.findByEmail(request.getUsername())
                    .orElseThrow( () -> new RuntimeException("Username Not Found "));

         if( !passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");

         }
        return jwtUtil.generateToken(user) ; 
    } 

   




    

}
