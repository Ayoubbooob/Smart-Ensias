package com.ensias.ensiasattendease.resources.responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.UnexpectedTypeException;

@RestControllerAdvice
public class UnexceptedTypeRespnseManager {

    @ResponseStatus(code = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Map<String , String> getUnexceptedTypeResponse(UnexpectedTypeException error) {
        Map<String, String> response = new HashMap<>();
        response.put("error", error.getMessage());
        return response;
    }

    
}
