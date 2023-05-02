package com.ensias.ensiasattendease.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.Student;
import com.ensias.ensiasattendease.resources.RequestModels.StudentRegisterRequest;
import com.ensias.ensiasattendease.resources.responses.StudentResponse;

public interface StudentService {
    List<Student> getAllStudent() ;
    Student enrollStudent(Student student ) ;
    Boolean registerAttendance(AttendanceModel attendance , String cne);
    Boolean deleteStudent(String cne);
    Collection<AttendanceModel> getStudentAttendance(String cne);

     Student registerStudent(StudentRegisterRequest request);

     StudentResponse getStudentById(String id);
    
}
