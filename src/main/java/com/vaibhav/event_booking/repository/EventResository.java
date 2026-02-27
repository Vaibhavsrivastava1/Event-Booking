package com.vaibhav.event_booking.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.event_booking.entity.Event;

import java.util.Optional;


public interface EventResository  extends JpaRepository<Event , Long> {

    Optional<Event>  findByTittle(String tittle);

    

}
