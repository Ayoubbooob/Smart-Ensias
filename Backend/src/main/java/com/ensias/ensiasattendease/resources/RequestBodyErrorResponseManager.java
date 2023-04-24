package com.ensias.ensiasattendease.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RequestBodyErrorResponseManager {

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ExceptionHandler
    public Map<String  , String >handleUserFielError(ConstraintViolationException err){
        Map<String , String > map = new HashMap<>(); 
        err.getConstraintViolations().forEach(e->{
            map.put(e.getPropertyPath().toString() , e.getMessage()); 
        });

        return map ; 
        
    }
}
