package com.ensias.ensiasattendease.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.JustificationModel;
import com.ensias.ensiasattendease.services.JustificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor 
@RequestMapping("/justification")
public class JustificationController {

    @Autowired
    private final JustificationService justificationService ; 

    @GetMapping()
    public ResponseEntity<List<JustificationModel>> getAllJusitification(){
        return new ResponseEntity<>(justificationService.getAllJustification() , HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> creatJustification(@RequestBody AttendanceModel attendance , @RequestBody JustificationModel justification){
        if(justification == null || attendance.getId() == null){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST) ; 
        }
        else{
            return new ResponseEntity<>(justificationService.createJustification(attendance.getId() , justification) , HttpStatus.CREATED);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJustificationById(@PathVariable Long id){
        if(id == null){
            return new ResponseEntity<>("error : justification id not existe"  , HttpStatus.BAD_REQUEST) ; 
        }
        else{
            return new ResponseEntity<>(justificationService.getJustificationById(id) , HttpStatus.OK);
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

    @PatchMapping()
    public ResponseEntity<?> updateJustification(@RequestBody JustificationModel justification){
        if(justification == null){
            return new ResponseEntity<>("error : justification cant not be null"  ,HttpStatus.BAD_REQUEST) ; 
        }
        else{
            if(justificationService.updateJustification(justification)==null){
                return new ResponseEntity<>("error : justification not found"  ,HttpStatus.BAD_REQUEST) ; 
            }
            return new ResponseEntity<>(justificationService.updateJustification(justification) , HttpStatus.ACCEPTED);
        }
    }

    
}
