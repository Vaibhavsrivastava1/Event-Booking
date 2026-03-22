package com.vaibhav.event_booking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vaibhav.event_booking.dto.EventRequest;
import com.vaibhav.event_booking.dto.EventResponse;
import com.vaibhav.event_booking.entity.Event;
import com.vaibhav.event_booking.repository.EventResository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    
 
    private final EventResository eventResository ;

   
    public String addEvent(EventRequest request) {

        if(eventResository.findByTittle(request.getTittle()).isPresent()){
              throw new  RuntimeException("Event already exist");

        }
         log.info("Adding an event ");
        Event event = Event.builder()
                      .tittle(request.getTittle())
                      .Description(request.getDescription())
                      .totalSeats(request.getTotalSeats())
                      .eventDate(LocalDate.parse(request.getEventDate()))
                      .availableSeats(request.getTotalSeats())
                      .build();

       eventResository.save(event);

       log.info("Event Added Successfully  ");


       return "Event Added Successfully";


        
    }

    @Cacheable(
    value = "events",
    key = "#p0.pageNumber + '-' + #p0.pageSize"
    )
    public List<EventResponse> getEvents( PageRequest page){


        log.info(" Getting list of events from database  ");

        return eventResository.findAll(page).
                getContent()
                .stream()
                .map(event -> EventResponse.builder()
                               .tittle(event.getTittle())
                                .Description(event.getDescription())
                                .availableSeats(event.getAvailableSeats())
                                .build()
                                 ).toList();
    }




}
