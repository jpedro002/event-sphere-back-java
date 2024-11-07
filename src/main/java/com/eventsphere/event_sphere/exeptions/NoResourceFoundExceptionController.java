package com.eventsphere.event_sphere.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class NoResourceFoundExceptionController {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(NoResourceFoundException e) {

        return new ResponseEntity<>(new NoResourceFoundExceptionControllerDTO("Not Found"), HttpStatus.NOT_FOUND);
    }

    record NoResourceFoundExceptionControllerDTO(String message) {
    }
}
