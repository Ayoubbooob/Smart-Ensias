package com.ensias.ensiasattendease.controllers;

import java.util.Collection;
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
import com.ensias.ensiasattendease.models.AttendanceStatus;
import com.ensias.ensiasattendease.models.StudentModel;
import com.ensias.ensiasattendease.services.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private final StudentService studentService ; 

    @GetMapping()
    public ResponseEntity<List<StudentModel>> getAllStudent(){
        return new ResponseEntity<>(studentService.getAllStudent() , HttpStatus.OK);
    }

    @GetMapping("/{cne}")
    public ResponseEntity<?> getStudentByCNE(@PathVariable String cne){
        if(cne == null){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST) ; 
        }
        else{
            if(studentService.getStudentByCNE(cne)==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>(studentService.getStudentByCNE(cne) , HttpStatus.OK);
        }
    }

    @GetMapping("/{cne}/attendances")
    public ResponseEntity<?> getStudenAllAttendances(@PathVariable  String cne){
        if(studentService.getStudentAllAttendance(cne)==null){
            return new ResponseEntity<>("error : Student do not exist" , HttpStatus.NOT_FOUND) ;
        }
        return new ResponseEntity<>(studentService.getStudentAllAttendance(cne) , HttpStatus.OK);
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
            if(studentService.deleteStudent(cne) == false){
                return new ResponseEntity<>("deleted : false" , HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>("deleted : true" , HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping()
    public ResponseEntity<?> updateStudent(@RequestBody StudentModel student){
        if(student == null){
            return new ResponseEntity<>("error : student can not be null "  , HttpStatus.BAD_REQUEST) ;
        }
        else{
            return new ResponseEntity<>(studentService.updateStudent(student) , HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping("/{cne}/attendance")
    public ResponseEntity<?> updateStudentAttendance(@RequestBody AttendanceModel attendance, @PathVariable String cne){
        if(cne == null || attendance == null){
            return new ResponseEntity<>("error : cne or attendance can not be null " , HttpStatus.BAD_REQUEST) ;
        }
        else{
            if(studentService.updateStudentAttendance(attendance, cne) == null){
                return new ResponseEntity<>("error : student or attendance do not exist" , HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>(studentService.updateStudentAttendance( attendance, cne) , HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/{cne}/attendance/{id}")
    public ResponseEntity<?> deleteStudentAttendance(@PathVariable String cne , @PathVariable Long id){
        if(cne == null || id == null){
            return new ResponseEntity<>("error : cne or id can not be null " , HttpStatus.BAD_REQUEST) ;
        }
        else{
            if(studentService.deleteStudentAttendance(cne, id) == false){
                return new ResponseEntity<>("error : student or attendance do not exist" , HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>("deleted : true" , HttpStatus.ACCEPTED);
        }
    }

    
}
