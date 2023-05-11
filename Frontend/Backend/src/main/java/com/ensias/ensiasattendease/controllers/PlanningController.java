package com.ensias.ensiasattendease.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ensias.ensiasattendease.models.PlanningModel;
import com.ensias.ensiasattendease.services.PlanningService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/planning")
@RequiredArgsConstructor
public class PlanningController {
    private final PlanningService planningService ;

    @GetMapping()
    public ResponseEntity<?> getAllPlanning(){
        return new ResponseEntity<>(planningService.getAllPlanning(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanningById(@PathVariable long page){
        PlanningModel pann =  planningService.getPlanningById(page) ; 
        if(pann == null){
            return new ResponseEntity<>("{\"error\" : \"planning not found\"}" , HttpStatus.BAD_REQUEST) ; 
        }
        return new ResponseEntity<>(pann , HttpStatus.OK);
    }
    
}
