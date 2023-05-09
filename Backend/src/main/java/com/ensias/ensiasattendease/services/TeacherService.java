package com.ensias.ensiasattendease.services;


import com.ensias.ensiasattendease.models.TeacherModel;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRegisterRequest;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRequestResponse;

import java.util.List;
import java.util.Map;

public interface TeacherService {

    TeacherModel registerTeacher(TeacherRegisterRequest request);

    List<TeacherRequestResponse> getAllTeachers();

    TeacherRequestResponse getTeacherById(Long teacherId);

    TeacherModel patchTeacher(Long teacherId, Map<String, Object> updates) throws Exception;

    void deleteTeacher(Long teacherId) throws Exception;
}
