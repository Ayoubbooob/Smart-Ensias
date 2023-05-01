package com.ensias.ensiasattendease.services;

import java.util.Collection;
import java.util.List;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.AttendanceStatus;
import com.ensias.ensiasattendease.models.StudentModel;

public interface StudentService {
    List<StudentModel> getAllStudent() ; 
    StudentModel enrollStudent(StudentModel student ) ; 
    StudentModel registerAttendance(AttendanceModel attendance , String cne);
    Boolean deleteStudent(String cne);
    Collection<AttendanceModel> getStudentAllAttendance(String cne);
    StudentModel getStudentByCNE(String cne);
    StudentModel updateStudent(StudentModel student);
    AttendanceModel updateStudentAttendance(AttendanceModel attendance , String cne);
    Boolean deleteStudentAttendance(String cne , Long id);
    // StudentModel getStudentByPhoneNumber(String phoneNumber);
    
}
