package com.ensias.ensiasattendease.controllers;

import java.util.Collection;
import java.util.List;

import com.ensias.ensiasattendease.resources.responses.StudentResponse;
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
import com.ensias.ensiasattendease.models.Student;
import com.ensias.ensiasattendease.services.implementations.StudentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentServiceImpl studentService ; 

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudent(){
        return new ResponseEntity<>(studentService.getAllStudent() , HttpStatus.OK);
    }

    @GetMapping("/attendance/{cne}")
    public ResponseEntity<Collection<AttendanceModel>> getStudentAttendance(@PathVariable String cne){
        return new ResponseEntity<>(studentService.getStudentAttendance(cne) , HttpStatus.OK);
    }

    @PostMapping("/enrollStudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        if(student == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }
        else{
            return new ResponseEntity<>(studentService.enrollStudent(student) , HttpStatus.CREATED);
        }
    }

    @PostMapping("/registerAttendance")
    public ResponseEntity<?> takeAttendance(@RequestBody AttendanceModel attendance , @RequestBody String cne){
        if(attendance == null || cne == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }
        else{
            return new ResponseEntity<>(studentService.registerAttendance(attendance , cne) , HttpStatus.CREATED);
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


    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable String id){
        if(id == null){
            return ResponseEntity.badRequest().body("The id must be set");
        }else{
            return ResponseEntity.ok(studentService.getStudentById(id));
        }
    }

    
}
