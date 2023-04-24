package com.ensias.ensiasattendease.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensias.ensiasattendease.models.JustificationModel;
import com.ensias.ensiasattendease.services.implementations.JustificationServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor 
@RequestMapping("/justification")
public class JustificationController {

    private final JustificationServiceImpl justificationService ; 

    @GetMapping()
    public ResponseEntity<List<JustificationModel>> getAllJusitification(){
        return new ResponseEntity<>(justificationService.getAllJustification() , HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> creatJustification(@RequestBody JustificationModel justification){
        if(justification == null){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST) ; 
        }
        else{
            return new ResponseEntity<>(justificationService.createJustification(justification) , HttpStatus.CREATED);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJustificationById(@PathVariable Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ; 
        }
        else{
            return new ResponseEntity<>(justificationService.deleteJustification(id) , HttpStatus.ACCEPTED);
        }

    }

    // @PathcMapping("/{id}")

    
}
