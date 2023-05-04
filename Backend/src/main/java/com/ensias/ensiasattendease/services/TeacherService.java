package com.ensias.ensiasattendease.services;


import com.ensias.ensiasattendease.models.TeacherModel;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRegisterRequest;

public interface TeacherService {

    TeacherModel registerTeacher(TeacherRegisterRequest request);
}
