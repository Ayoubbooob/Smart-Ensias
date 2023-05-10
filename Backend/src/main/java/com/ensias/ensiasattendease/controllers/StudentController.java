package com.ensias.ensiasattendease.controllers;

import java.util.Collection;
import java.util.List;

import com.ensias.ensiasattendease.services.implementations.NotificationServiceImpl;
import com.ensias.ensiasattendease.services.implementations.StudentServiceImpl;
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
import com.ensias.ensiasattendease.models.FiliereModel;
import com.ensias.ensiasattendease.models.StudentModel;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {


    private final StudentServiceImpl studentService;
    private final NotificationServiceImpl notificationService;


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
    public ResponseEntity<?> getStudentAttendance(@PathVariable String cne){
        Collection<AttendanceModel> coll = studentService.getStudentAllAttendance(cne);
        if(coll == null ){
            return new ResponseEntity<>("{\"error\" : \"student or attendances  do not exist\"}" , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentService.getStudentAllAttendance(cne) , HttpStatus.OK);
    }

//    @PostMapping("/enrollStudent")
//    public ResponseEntity<?> createStudent(@RequestBody StudentModel student){
//        if(student == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
//        }
//        else{
//            StudentModel studentModel = studentService.enrollStudent(student) ;
//            if(studentModel == null){
//                return new ResponseEntity<>("{\"error\" : \"field unique constraint must be respected\"}" , HttpStatus.BAD_REQUEST) ;
//            }
//            return new ResponseEntity<>(studentModel , HttpStatus.CREATED);
//        }
//    }

    @PostMapping("/registerAttendance/{cne}")
    public ResponseEntity<?> takeAttendance(@RequestBody AttendanceModel attendance  , @PathVariable String cne){
        if(attendance == null || cne == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }
        else{
            AttendanceModel attendanceModel = studentService.registerAttendance(attendance , cne) ;
            if( attendanceModel == null){
                return new ResponseEntity<>("{\"error\" : \"student  do not exist\"}" , HttpStatus.NOT_FOUND) ;
            }
            StudentModel student = studentService.getStudentByCNE(cne);
            int numberOfAbsences = student.getNumberOfAbsences();
            if(numberOfAbsences >= 3){
                notificationService.addNotification(student, "You have missed three or more classes.");
            }
            studentService.updateStudent(student);
            return new ResponseEntity<>( attendanceModel  , HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{cne}")
    public ResponseEntity<?> unEnrollStudent(@PathVariable String cne){
        if(cne == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }
        else{

            if(studentService.deleteStudent(cne) == false){
                return new ResponseEntity<>("{\"deleted\" : false}" , HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>("{\"deleted\" : true}" , HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping()
    public ResponseEntity<?> updateStudent(@RequestBody StudentModel student){
        if(student == null){
            return new ResponseEntity<>("{\"error\" : \"student can not be null\" }"  , HttpStatus.BAD_REQUEST) ;
        }
        else{
            if(studentService.updateStudent(student) == null){
                return new ResponseEntity<>("{\"error\" : \"student do not exist\"}" , HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>(studentService.updateStudent(student) , HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping("/{cne}/attendance")
    public ResponseEntity<?> updateStudentAttendance(@RequestBody AttendanceModel attendance, @PathVariable String cne){
        if(cne == null || attendance == null){
            return new ResponseEntity<>("{\"error\" : \"cne or attendance can not be null\" }" , HttpStatus.BAD_REQUEST) ;
        }
        else{
            if(studentService.updateStudentAttendance(attendance, cne) == null){
                return new ResponseEntity<>("{\"error\" : \"student do not exist or don't have attendance\"}" , HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>(studentService.updateStudentAttendance( attendance, cne) , HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/{cne}/attendance/{id}")
    public ResponseEntity<?> deleteStudentAttendance(@PathVariable String cne , @PathVariable Long id){
        if(cne == null || id == null){
            return new ResponseEntity<>("{\"error\" : \"cne or id can not be null\"} " , HttpStatus.BAD_REQUEST) ;
        }
        else{
            if(studentService.deleteStudentAttendance(cne, id) == false){
                return new ResponseEntity<>("{\"error\" : \"student or attendance do not exist\"}" , HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>("{\"deleted\" : true}" , HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/{cne}/filiere")
    public ResponseEntity<?> getStudentFiliere(@PathVariable String cne){
        if(cne == null){
            return new ResponseEntity<>("{\"error\" : \"cne can not be null\"}" , HttpStatus.BAD_REQUEST) ;
        }
        else{
            FiliereModel filiereModel = studentService.getStudentFiliere(cne) ;
            if(filiereModel == null){
                return new ResponseEntity<>("{\"error\" : \"student do not exist or don't have filiere\"}" , HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>(filiereModel , HttpStatus.OK);
        }
    }

    @PostMapping("/{cne}/filiere/{id}")
    public ResponseEntity<?> addStudentFiliere(@PathVariable String cne , @PathVariable Long id){
        if(cne == null || id == null){
            return new ResponseEntity<>("{\"error\" : \"cne or filiere can not be null\"}" , HttpStatus.BAD_REQUEST) ;
        }
        else{
            FiliereModel filiereModel = studentService.affectStudentFiliere(cne, id) ;
            if(filiereModel == null){
                return new ResponseEntity<>("{\"error\" : \"student do not exist or filiere already exist\"}" , HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>(filiereModel , HttpStatus.CREATED);
        }
    }

    @PatchMapping("/{cne}/filiere")
    public ResponseEntity<?> updateStudentFiliere(@PathVariable String cne , @RequestBody FiliereModel filiere){
        if(cne == null || filiere == null){
            return new ResponseEntity<>("{\"error\" : \"cne or filiere can not be null\"}" , HttpStatus.BAD_REQUEST) ;
        }
        else{
            FiliereModel filiereModel = studentService.updateStudentFiliere(cne, filiere) ;
            if(filiereModel == null){
                return new ResponseEntity<>("{\"error\" : \"student do not exist or filiere do not exist\"}" , HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>(filiereModel , HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/{cne}/filiere")
    public ResponseEntity<?> deleteStudentFiliere(@PathVariable String cne){
        if(cne == null){
            return new ResponseEntity<>("{\"error\" : \"cne can not be null\"}" , HttpStatus.BAD_REQUEST) ;
        }
        else{
            Boolean returnValue = studentService.deleteStudentFiliere(cne) ;
            if( returnValue == null){
                return new ResponseEntity<>("{\"error\" : \"student do not exist or don't have filiere\"}" , HttpStatus.NOT_FOUND) ;
            }
            return new ResponseEntity<>("{\"deleted\" : true}" , HttpStatus.ACCEPTED);
        }
    }


    @GetMapping("/getstudent/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable String id){
        if(id == null){
            return ResponseEntity.badRequest().body("The id must be set");
        }else{
            return ResponseEntity.ok(studentService.getStudentById(id));
        }
    }

    
}
