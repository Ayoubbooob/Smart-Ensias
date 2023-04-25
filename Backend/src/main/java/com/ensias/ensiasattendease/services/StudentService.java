package com.ensias.ensiasattendease.services;

import java.util.Collection;
import java.util.List;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.StudentModel;

public interface StudentService {
    List<StudentModel> getAllStudent() ; 
    StudentModel enrollStudent(StudentModel student ) ; 
    Boolean registerAttendance(AttendanceModel attendance , String cne);
    Boolean deleteStudent(String cne);
    Collection<AttendanceModel> getStudentAttendance(String cne);
    
}
