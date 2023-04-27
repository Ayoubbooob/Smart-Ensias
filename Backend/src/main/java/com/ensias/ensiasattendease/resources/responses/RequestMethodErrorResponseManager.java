package com.ensias.ensiasattendease.resources.responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class RequestMethodErrorResponseManager {

    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler
    public Map<String , String> handleRequestMthodError(MethodArgumentTypeMismatchException err){
        Map<String , String> map = new HashMap() ;
        map.put(err.getErrorCode() , "la methode de la requête est invalide") ;
        return map ; 
    }
    
}
