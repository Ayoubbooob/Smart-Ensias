package com.ensias.ensiasattendease.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.ensias.ensiasattendease.models.AttendanceModel;
<<<<<<< HEAD
import com.ensias.ensiasattendease.models.AttendanceStatus;
import com.ensias.ensiasattendease.models.FiliereModel;
import com.ensias.ensiasattendease.models.StudentModel;
import com.ensias.ensiasattendease.resources.RequestModels.StudentRegisterRequest;
import com.ensias.ensiasattendease.resources.responses.StudentResponse;

public interface StudentService {
    List<StudentModel> getAllStudent() ; 
    StudentModel enrollStudent(StudentModel student ) ; 
    AttendanceModel registerAttendance(AttendanceModel attendance , String cne);
    Boolean deleteStudent(String cne);
    Collection<AttendanceModel> getStudentAllAttendance(String cne);
    StudentModel getStudentByCNE(String cne);
    StudentModel updateStudent(StudentModel student);
    AttendanceModel updateStudentAttendance(AttendanceModel attendance , String cne);
    Boolean deleteStudentAttendance(String cne , Long id);
    FiliereModel getStudentFiliere(String cne);
    FiliereModel affectStudentFiliere(String cne , Long id);
    FiliereModel updateStudentFiliere(String cne , FiliereModel filiere);
    Boolean deleteStudentFiliere(String cne);
    StudentModel registerStudent(StudentRegisterRequest request);
    StudentResponse getStudentById(String id);
    
}
