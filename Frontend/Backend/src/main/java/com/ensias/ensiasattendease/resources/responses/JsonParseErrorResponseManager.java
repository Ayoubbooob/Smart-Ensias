package com.ensias.ensiasattendease.resources.responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonParseException;

@RestControllerAdvice
public class JsonParseErrorResponseManager {

    @ResponseStatus(code = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Map<String , String> handleJsonParseException(JsonParseException ex){
        Map<String , String> map = new HashMap<>();
        map.put("error", "Json is mal formed");
        return map ;
    }
    
}
