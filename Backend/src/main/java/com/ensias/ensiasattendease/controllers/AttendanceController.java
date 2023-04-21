package com.ensias.ensiasattendease.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensias.ensiasattendease.repositories.AttendanceRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceRepository attendanceRepository;

    @GetMapping("/attendance")
    public String getAttendance(){
        return "hello welcome to attendance" ; 
    }
}
