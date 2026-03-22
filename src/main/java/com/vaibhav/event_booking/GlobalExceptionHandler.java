package com.vaibhav.event_booking;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vaibhav.event_booking.Exceptions.ResourceNotFound;
import com.vaibhav.event_booking.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

      @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {


       log.error("Exception Occured ", ex);
       ErrorResponse error = new ErrorResponse();
       error.setSuccess(false);
       error.setMessage("Runtime Error");
       error.setErrors(Map.of("Exception occured", ex.getMessage()));
       error.setTimestamp(LocalDateTime.now());

       return ResponseEntity.badRequest().body(error);
      
    }

    @ExceptionHandler(ResourceNotFound.class)
    public  ResponseEntity<ErrorResponse>  handleResourceNotFoundException(ResourceNotFound ex){
         log.error("Exception Occured ", ex);
         ErrorResponse error = new ErrorResponse();
       error.setSuccess(false);
       error.setMessage("Resource Not Found Error");
       error.setErrors(Map.of("Exception occured", ex.getMessage()));
       error.setTimestamp(LocalDateTime.now());
         return ResponseEntity.badRequest().body(error);
    }

      @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
          ErrorResponse error = new ErrorResponse();
       error.setSuccess(false);
       error.setMessage("Validation Error");
       error.setErrors(errors);
       error.setTimestamp(LocalDateTime.now());

        return ResponseEntity.badRequest().body(error);
    }

}
