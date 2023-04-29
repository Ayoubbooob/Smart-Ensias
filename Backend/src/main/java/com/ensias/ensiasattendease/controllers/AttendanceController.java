package com.ensias.ensiasattendease.controllers;

import java.util.List;
import java.util.Optional;

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
import com.ensias.ensiasattendease.models.JustificationModel;
import com.ensias.ensiasattendease.services.AttendanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {
    
    @Autowired
    private final  AttendanceService  attendanceService;

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
                return new ResponseEntity<>("error : attendance dont have justification" , HttpStatus.BAD_REQUEST) ; 
            }
            return new ResponseEntity<>(attendanceService.getAttendanceJustification(id) , HttpStatus.OK) ;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id){
        if(id == null){
            return new ResponseEntity<>( "error : parameter id is required", HttpStatus.BAD_REQUEST) ; 
        }
        else{
            if(attendanceService.deleteAttendance(id) == false){
                return new ResponseEntity<>("error : attendance not found with this id" , HttpStatus.BAD_REQUEST) ; 
            }
            return new ResponseEntity<>("deleted : true " , HttpStatus.OK);
        }
    }



    @PatchMapping()
    public ResponseEntity<?> updateAttendance(@RequestBody AttendanceModel attendance){
        if(attendance == null){
            return new ResponseEntity<>("error : attendance must provide" , HttpStatus.BAD_REQUEST) ; 
        }
        else{
            if(attendanceService.updateAttendance(attendance) == null){
                return new ResponseEntity<>("error : attendance not found" , HttpStatus.BAD_REQUEST) ; 
            }
            else{
                return new ResponseEntity<>(attendanceService.updateAttendance(attendance) , HttpStatus.OK) ; 
            }
        }
    }

    @PatchMapping("/{id}/justification")
    public ResponseEntity<?> updateAttendanceJustification(@PathVariable Long id , @RequestBody JustificationModel justification){
        if(id == null || justification == null){
            return new ResponseEntity<>("error : the body id and attendance are required" , HttpStatus.BAD_REQUEST) ; 
        }
        else{
            if(attendanceService.updateAttendanceJustification(justification , id) == null){
                return new ResponseEntity<>("error : attendance not found" , HttpStatus.BAD_REQUEST) ; 
            }
            else{
                return new ResponseEntity<>(attendanceService.updateAttendanceJustification(justification , id) , HttpStatus.OK) ; 
            }
        }
    }

    @DeleteMapping("/{id}/justification/{idJustification}")
    public ResponseEntity<?> deleteAttendanceJustification(@PathVariable Long id , @PathVariable Long idJustification){
        if(id == null || idJustification == null){
            return new ResponseEntity<>("error : the body id and attendance are required" , HttpStatus.BAD_REQUEST) ; 
        }
        else{
            if(attendanceService.deleteAttendanceJustification(idJustification) == false){
                return new ResponseEntity<>("error : attendance not found" , HttpStatus.BAD_REQUEST) ; 
            }
            else{
                return new ResponseEntity<>(attendanceService.deleteAttendanceJustification(idJustification) , HttpStatus.OK) ; 
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendanceById(@PathVariable Long id){
        if(id == null){
            return new ResponseEntity<>("error : id must provide" , HttpStatus.BAD_REQUEST) ; 
        }
        else{
            Optional<AttendanceModel> attendance = attendanceService.getAttendanceById(id) ; 
            if(attendance.isPresent()){
                return new ResponseEntity<>(attendance.get() , HttpStatus.OK) ; 
            }
            else{
                return new ResponseEntity<>("error : attendance not found" , HttpStatus.BAD_REQUEST) ; 
            }
        }
    }
    

}
