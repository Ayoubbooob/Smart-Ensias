package com.ensias.ensiasattendease.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
