package com.vaibhav.event_booking.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.vaibhav.event_booking.dto.EventRequest;
import com.vaibhav.event_booking.entity.Event;
import com.vaibhav.event_booking.repository.EventResository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    
 
    private final EventResository eventResository ;

   
    public String addEvent(EventRequest request) {

        if(eventResository.findByTittle(request.getTittle()).isPresent()){
              throw new  RuntimeException("Event already exist");

        }
        Event event = Event.builder()
                      .tittle(request.getTittle())
                      .Description(request.getDescription())
                      .totalSeats(request.getTotalSeats())
                      .eventDate(LocalDate.parse(request.getEventDate()))
                      .availableSeats(request.getTotalSeats())
                      .build();

       eventResository.save(event);

       return "Event Added Successfully";


        
    }




}
