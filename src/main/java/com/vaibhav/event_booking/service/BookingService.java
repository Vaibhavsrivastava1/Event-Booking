package com.vaibhav.event_booking.service;


import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vaibhav.event_booking.Exceptions.ResourceNotFound;
import com.vaibhav.event_booking.dto.BookingResponse;
import com.vaibhav.event_booking.entity.Bookings;
import com.vaibhav.event_booking.entity.Event;
import com.vaibhav.event_booking.entity.User;
import com.vaibhav.event_booking.repository.BookingRepository;
import com.vaibhav.event_booking.repository.EventResository;
import com.vaibhav.event_booking.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final UserRepository userRepository ;
    private final BookingRepository bookingRepository ; 
    private final EventResository eventResository;

    public String bookEvent( String email , Long eventId){

        User user =  userRepository.findByEmail(email)
                    .orElseThrow(() ->  new  ResourceNotFound("User not  Found"));

         Event event = eventResository.findById(eventId)
                       .orElseThrow(()-> new ResourceNotFound("Evemt not found")); 
                       
        if(bookingRepository.existsByUserAndEvent(user, event)){
            throw new RuntimeException("Already Booked");
        }               
                       
         if(event.getAvailableSeats()<=0){
              throw new RuntimeException("Event is full");
         } 
         log.info("Starting the booking ..{}" , event.getTittle());
         
         event.setAvailableSeats(event.getAvailableSeats() -1);

         Bookings  booking = Bookings.builder()
                             .event(event)
                             .user(user)
                             .build();
                             
            bookingRepository.save(booking);
            eventResository.save(event);

            log.info( "Booking done of event ..{}" , event.getTittle());
            return "Booking Done Successfully";

    }
     public List<BookingResponse> getMyBookings(String email , PageRequest page ){
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFound("User not found"));

          log.info("Getting list of bookings for user - {}", user.getName());   

        return bookingRepository.findByUser(user,page).getContent()
                   .stream()
                   .map(item -> BookingResponse.builder()
                                .bookingId(item.getId())
                                .eventTitle(item.getEvent().getTittle())
                                .eventDate(item.getEvent().getEventDate())
                                .bookedAt(item.getBookedAt())
                                .build() )
                                .toList();


    }



    @Transactional
    public void cancelBooking(String email , Long BookingId){

        Bookings booking =   bookingRepository.findById(BookingId)
                             .orElseThrow(() -> new ResourceNotFound("Booking not found"));

          if(!booking.getUser().getEmail().equals(email)){

             throw new RuntimeException("You cannot delete this booking");
              
          }    


          log.info("Deleting booking .....");
          
          
          Event event = booking.getEvent();

          event.setAvailableSeats(event.getAvailableSeats() + 1);

          bookingRepository.delete(booking);
          log.info("Booking Deleted ");

        


    }

}
