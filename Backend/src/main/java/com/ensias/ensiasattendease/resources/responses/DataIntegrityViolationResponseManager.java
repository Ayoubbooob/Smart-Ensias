package com.ensias.ensiasattendease.resources.responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataIntegrityViolationResponseManager {
    
    @ResponseStatus(code = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Map<String , String> handleDataIntegrityViolationException(DataIntegrityViolationException except){
        Map<String , String> map = new HashMap<>();
        map.put("erreur", except.getMessage());
        return map ;
    }

}
