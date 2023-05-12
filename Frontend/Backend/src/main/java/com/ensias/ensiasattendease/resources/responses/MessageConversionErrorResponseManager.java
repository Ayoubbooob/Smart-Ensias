package com.ensias.ensiasattendease.resources.responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MessageConversionErrorResponseManager {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Map<String , String> handleMessageConversionError(HttpMessageConversionException except){
        Map<String , String> map = new HashMap<>() ;
        map.put("erreur" , except.getMessage()) ;
        return map ; 
    }
    
}
