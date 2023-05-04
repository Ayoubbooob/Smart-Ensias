package com.ensias.ensiasattendease.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ensias.ensiasattendease.models.CourseModel;
import com.ensias.ensiasattendease.models.FiliereModel;
import com.ensias.ensiasattendease.services.FiliereService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filiere")
public class FiliereController {

    @Autowired
    private final FiliereService filiereService;

    @GetMapping()
    public ResponseEntity<?> getAllFiliere(@RequestParam(name = "page" , defaultValue = "0") int page , @RequestParam(name = "size" , defaultValue = "5") int size){
        return new ResponseEntity<>(filiereService.getAllFiliere(page , size), HttpStatus.OK);
    }
    @GetMapping("{id}/course")
    public ResponseEntity<?> getAllFiliereCourse(@RequestParam(name = "page" , defaultValue = "0") int page , @RequestParam(name = "size" , defaultValue = "5") int size , @PathVariable Long id){
        Collection course = filiereService.getAllFiliereCourse(id);
        if(course == null){
            return new ResponseEntity<>("{\"error\" : \"filiere not found\"}" , HttpStatus.BAD_REQUEST) ; 
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createFiliere(@RequestBody FiliereModel filiere){
        if(filiere == null ){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST) ; 
        }
        else{
            FiliereModel filierTemp = filiereService.addFilier(filiere) ;
            if(filierTemp == null){
                return new ResponseEntity<>("{\"error\" : \"filiere already exist or there is an error\"}" , HttpStatus.BAD_REQUEST) ; 
            }
            return new ResponseEntity<>(filierTemp , HttpStatus.CREATED) ;
        }
    }

    @PostMapping("{id}/course/add")
    public ResponseEntity<?> addCourseToFilier(@PathVariable Long id , @RequestBody CourseModel course){
        if(id == null || course == null){
            return new ResponseEntity<>("{\"error\" : \"the body id and attendance are required\"}" , HttpStatus.BAD_REQUEST) ; 
        }
        else{
            CourseModel courseTemp =  filiereService.addCourseToFilier(id, course);
            if(courseTemp == null){
                return new ResponseEntity<>( "{\"error\" : \"filiere not found\"}", HttpStatus.BAD_REQUEST) ; 
            }
            return new ResponseEntity<>(courseTemp, HttpStatus.CREATED);
        }
    }
    
}
