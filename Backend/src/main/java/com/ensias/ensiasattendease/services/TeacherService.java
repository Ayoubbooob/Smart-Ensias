package com.ensias.ensiasattendease.services;


import com.ensias.ensiasattendease.models.Teacher;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRegisterRequest;

public interface TeacherService {

    Teacher registerTeacher(TeacherRegisterRequest request);
}
