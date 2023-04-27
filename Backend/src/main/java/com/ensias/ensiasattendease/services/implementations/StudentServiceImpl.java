package com.ensias.ensiasattendease.services.implementations;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.AttendanceStatus;
import com.ensias.ensiasattendease.models.StudentModel;
import com.ensias.ensiasattendease.repositories.AttendanceRepository;
import com.ensias.ensiasattendease.repositories.StudentRepository;
import com.ensias.ensiasattendease.services.StudentService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository ;

    @Autowired
    private AttendanceRepository attendanceRepository ; 


    public StudentServiceImpl(StudentRepository studentRepository ){
        this.studentRepository = studentRepository ; 
    }

    @Override
    public List<StudentModel> getAllStudent(){
        return studentRepository.findAll();
    }

    @Override
    public StudentModel enrollStudent(StudentModel student){
        return studentRepository.save(student);
    }

    /**
     * @target this methode is used to register student attendance 
     */
    @Override
    public Boolean registerAttendance(AttendanceStatus status , String cne){
        try {
            StudentModel student = studentRepository.findByCNE(cne);
            AttendanceModel newAttendanceModel = new AttendanceModel();
            newAttendanceModel.setStatus(status);
            newAttendanceModel.setDateTime(new Date(System.currentTimeMillis()));
            newAttendanceModel.getStudent().add(student);
            attendanceRepository.save(newAttendanceModel);
            student.getAttendances().add(newAttendanceModel);
            studentRepository.save(student);
            return true ;
        } catch (Exception e) {
            // TODO: handle exception
            return false ;
        }

    }

    @Override
    public Boolean deleteStudent(String cne){
        try {
            studentRepository.deleteByCNE(cne);
            return true ;
        } catch (Exception e){
            return false ;
        }
    }

    @Override 
    public Collection<AttendanceModel> getStudentAttendance(String cne){
        StudentModel student = studentRepository.findByCNE(cne);
        return student.getAttendances();
    }

    
    
}
