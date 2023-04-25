package com.ensias.ensiasattendease.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.services.implementations.AttendanceServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {
    
    private final AttendanceServiceImpl  attendanceService;

    @GetMapping
    public ResponseEntity<List<AttendanceModel>> getAttendance(){
        return new ResponseEntity<>(attendanceService.getAllAttendance() , HttpStatus.OK); 
    }

    @PostMapping("/add")
    public ResponseEntity<?> createAttendance(@RequestBody AttendanceModel attendance){
        if(attendance == null ){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST) ; 
        }
        else{
            return new ResponseEntity<>(attendanceService.saveAttendanceModel(attendance) , HttpStatus.CREATED) ;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<AttendanceModel>> getAttendanceById(@PathVariable Long id){
        return new ResponseEntity<>(attendanceService.getAttendanceById(id) , HttpStatus.OK);
    }
}
