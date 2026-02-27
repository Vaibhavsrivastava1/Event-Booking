package com.vaibhav.event_booking.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Event")
public class Event {
    

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;

    private String tittle ;

    private String Description ; 

    private LocalDate eventDate;

    private Long totalSeats ; 



    private Long availableSeats ; 



}
