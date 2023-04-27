package com.ensias.ensiasattendease.controllers;

import java.util.Collection;
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

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.AttendanceStatus;
import com.ensias.ensiasattendease.models.AttendanceType;
import com.ensias.ensiasattendease.models.StudentModel;
import com.ensias.ensiasattendease.services.implementations.StudentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceImpl studentService ; 

    @GetMapping()
    public ResponseEntity<List<StudentModel>> getAllStudent(){
        return new ResponseEntity<>(studentService.getAllStudent() , HttpStatus.OK);
    }

    @GetMapping("/{cne}")
    public ResponseEntity<Collection<AttendanceModel>> getStudentAttendance(@PathVariable String cne){
        return new ResponseEntity<>(studentService.getStudentAttendance(cne) , HttpStatus.OK);
    }

    @PostMapping("/enrollStudent")
    public ResponseEntity<?> createStudent(@RequestBody StudentModel student){
        if(student == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }
        else{
            return new ResponseEntity<>(studentService.enrollStudent(student) , HttpStatus.CREATED);
        }
    }

    @PostMapping("/registerAttendance/{cne}")
    public ResponseEntity<?> takeAttendance(@RequestBody String status  , @PathVariable String cne){
        if(status == null || cne == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }
        else{
            return new ResponseEntity<>( studentService.registerAttendance(AttendanceStatus.valueOf(status) , cne)  , HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{cne}")
    public ResponseEntity<?> unEnrollStudent(@PathVariable String cne){
        if(cne == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }
        else{
            return new ResponseEntity<>(studentService.deleteStudent(cne) , HttpStatus.ACCEPTED);
        }
    }

    
}
