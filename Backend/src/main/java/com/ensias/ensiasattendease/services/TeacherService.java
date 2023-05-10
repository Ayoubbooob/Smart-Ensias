package com.ensias.ensiasattendease.services;


import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.TeacherModel;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRegisterRequest;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRequestResponse;
import com.ensias.ensiasattendease.resources.responses.TeacherAttendanceResponse;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TeacherService {

    TeacherModel registerTeacher(TeacherRegisterRequest request);

    List<TeacherRequestResponse> getAllTeachers();

    TeacherRequestResponse getTeacherById(Long teacherId);

    TeacherModel patchTeacher(Long teacherId, Map<String, Object> updates) throws Exception;

    void deleteTeacher(Long teacherId) throws Exception;


//     List<AttendanceModel> getTeacherAttendances(Long id) throws Exception;

    List<TeacherAttendanceResponse> getTeacherAttendances(String matricule) throws Exception;
     AttendanceModel affectTeacherAttendance(AttendanceModel attendance, String matricule) throws Exception;

}
