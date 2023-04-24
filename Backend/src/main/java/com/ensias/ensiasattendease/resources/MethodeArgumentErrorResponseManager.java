package com.ensias.ensiasattendease.resources ;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class MethodeArgumentErrorResponseManager{

    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Map<String , String> handleMethodArgumentNotValidException(MethodArgumentTypeMismatchException err){

        Map<String , String> map = new HashMap<>() ;
        map.put(err.getName() , "le corps de la requête ne doit pas être vide");

        return map ; 
    }

}



