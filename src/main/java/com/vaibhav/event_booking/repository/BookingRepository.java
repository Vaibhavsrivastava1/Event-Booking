package com.vaibhav.event_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.event_booking.entity.Bookings;
import com.vaibhav.event_booking.entity.Event;
import com.vaibhav.event_booking.entity.User;
import java.util.List;


public interface BookingRepository extends JpaRepository<Bookings , Long> {

     boolean existsByUserAndEvent(User user, Event event);

     List<Bookings> findByUser(User user);


}
