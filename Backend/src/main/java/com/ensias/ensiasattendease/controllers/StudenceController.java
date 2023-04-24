package com.ensias.ensiasattendease.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensias.ensiasattendease.models.StudentModel;
import com.ensias.ensiasattendease.services.implementations.StudentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("students")
public class StudenceController {
    
    private final StudentServiceImpl studentService ; 

    @GetMapping()
    public ResponseEntity<List<StudentModel>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudent() , HttpStatus.OK) ;
    }
    
}
