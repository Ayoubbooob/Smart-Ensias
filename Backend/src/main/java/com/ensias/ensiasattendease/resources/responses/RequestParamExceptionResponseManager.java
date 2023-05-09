package com.ensias.ensiasattendease.resources.responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestParamExceptionResponseManager {

    public Map<String , String> handleRequestParamException(MissingServletRequestParameterException ex){
        Map<String , String> response = new HashMap<>() ;
        response.put("error" , ex.getMessage()) ;
        return response ;
    }
    
}
