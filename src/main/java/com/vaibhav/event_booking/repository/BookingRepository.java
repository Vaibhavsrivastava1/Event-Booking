package com.vaibhav.event_booking.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.event_booking.entity.Bookings;
import com.vaibhav.event_booking.entity.Event;
import com.vaibhav.event_booking.entity.User;
import java.util.List;


public interface BookingRepository extends JpaRepository<Bookings , Long> {

     boolean existsByUserAndEvent(User user, Event event);
     
     @EntityGraph(attributePaths = {"event"})
     Page<Bookings> findByUser(User user , PageRequest page );




}
