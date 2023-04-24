package com.ensias.ensiasattendease.resources ;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MethodeArgumentErrorResponseManager{

    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Map<String , String> handleMethodArgumentNotValidException(HttpMessageNotReadableException err){

        Map<String , String> map = new HashMap<>() ;
        map.put("cause" , err.getMessage());

        return map ; 
    }

}



