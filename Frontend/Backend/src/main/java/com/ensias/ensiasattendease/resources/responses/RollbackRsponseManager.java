package com.ensias.ensiasattendease.resources.responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RollbackRsponseManager {

    @ResponseStatus(code = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Map<String, String> rollbackResponse(UnexpectedRollbackException exceptio){
        Map<String, String> response = new HashMap<>();
        response.put("error", "an field are duplicated please change");
        return response;
    }
    
}
