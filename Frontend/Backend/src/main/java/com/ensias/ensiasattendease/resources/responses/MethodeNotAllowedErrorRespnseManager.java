package com.ensias.ensiasattendease.resources.responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MethodeNotAllowedErrorRespnseManager {

    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler
    public Map<String , String> methodeNotAllowedError(HttpRequestMethodNotSupportedException except){
        Map<String , String> map = new HashMap<>();
        map.put("erreur", except.getMessage());
        return map ; 
    }
    
}
