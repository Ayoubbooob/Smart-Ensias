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
import com.ensias.ensiasattendease.models.JustificationModel;
import com.ensias.ensiasattendease.services.implementations.AttendanceServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {
    
    private final  AttendanceServiceImpl  attendanceService;

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

    @PostMapping("{id}/justification/add")
    public ResponseEntity<?> addJustification(@PathVariable Long id , @RequestBody JustificationModel justification){
        if(id == null || justification == null){
            return new ResponseEntity<>("error : the body id and attendance are required" , HttpStatus.BAD_REQUEST) ; 
        }
        else{
            if(attendanceService.addJustificationToAttendance(id, justification) == null){
                return new ResponseEntity<>( "error : attendance not found", HttpStatus.BAD_REQUEST) ; 
            }
            else{
                return new ResponseEntity<>(attendanceService.addJustificationToAttendance(id, justification) , HttpStatus.CREATED) ;
            }
        }
    }

    @GetMapping("/{id}/justification")
    public ResponseEntity<?> getAttendanceJustification(@PathVariable Long id){
        if(id == null){
            return  new ResponseEntity<>("error : id must provide"  , HttpStatus.BAD_REQUEST) ;
        }
        else{
            if(attendanceService.getAttendanceJustification(id) == null){
                return new ResponseEntity<>("error : attendance not found" , HttpStatus.BAD_REQUEST) ; 
            }
            return new ResponseEntity<>(attendanceService.getAttendanceJustification(id) , HttpStatus.OK) ;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<AttendanceModel>> getAttendanceById(@PathVariable Long id){
        return new ResponseEntity<>(attendanceService.getAttendanceById(id) , HttpStatus.OK);
    }
}
