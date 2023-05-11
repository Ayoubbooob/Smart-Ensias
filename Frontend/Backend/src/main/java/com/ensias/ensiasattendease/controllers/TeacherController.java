package com.ensias.ensiasattendease.controllers;


import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.TeacherModel;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRequestResponse;
import com.ensias.ensiasattendease.resources.responses.TeacherAttendanceResponse;
import com.ensias.ensiasattendease.services.implementations.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherServiceImpl teacherService;


//    @PostMapping("/create")
//    public ResponseEntity<TeacherModel> createTeacher(@RequestBody AddTeacherRequest addTeacherRequest) throws Exception {
//        try {
//            TeacherModel teacher = teacherService.createTeacher(addTeacherRequest);
//            return ResponseEntity.ok(teacher);
//        } catch (Exception e) {
//            throw new Exception("Failed to update teacher: " + e.getMessage());
//        }
//    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherRequestResponse>> getAllTeachers() throws Exception{
        try{
            List<TeacherRequestResponse> teachers =   teacherService.getAllTeachers();
            return ResponseEntity.ok(teachers);
        }catch(Exception e){
            throw new Exception("Failed to get teachers: " + e.getMessage());
        }
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherRequestResponse> getTeacherById(@PathVariable Long teacherId) throws Exception {
        try {
            TeacherRequestResponse teacher = teacherService.getTeacherById(teacherId);
            if(teacher == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(teacher);
        } catch (Exception e) {
            throw new Exception("Failed to get teacher: " + e.getMessage());
        }
    }



    @PatchMapping("/{teacherId}")
    public ResponseEntity<TeacherModel> patchTeacher(@PathVariable Long teacherId, @RequestBody Map<String, Object> updates) throws Exception {
        try {
            TeacherModel patchedTeacher = teacherService.patchTeacher(teacherId, updates);
            return ResponseEntity.ok(patchedTeacher);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();        }
    }
//
    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) throws Exception {
        try {
            teacherService.deleteTeacher(teacherId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/attendances/{matricule}")
    public ResponseEntity<List<TeacherAttendanceResponse>> getTeacherAttendances(@PathVariable String matricule) throws Exception{
        try{
            List<TeacherAttendanceResponse> attendances = teacherService.getTeacherAttendances(matricule);
            return ResponseEntity.ok(attendances);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/affectAttendance/{matricule}")
    public ResponseEntity<AttendanceModel> affectTeacherAttendance(@RequestBody AttendanceModel attendance  , @PathVariable String matricule){
        try{
            AttendanceModel attendanceAffected = teacherService.affectTeacherAttendance(attendance , matricule) ;
            return  ResponseEntity.ok(attendanceAffected);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }




    //TODO -  all fields required to update a teacher
//    @PutMapping("/{teacherId}")
//    public ResponseEntity<TeacherModel> updateTeacher(@PathVariable Long teacherId, @RequestBody AddTeacherRequest addTeacherRequest) throws Exception {
//        try {
//            TeacherModel updatedTeacher = teacherService.updateTeacher(teacherId, addTeacherRequest);
//            return ResponseEntity.ok(updatedTeacher);
//        } catch (Exception e) {
//            throw new Exception("Failed to update teacher: " + e.getMessage());
//        }
//    }
}
