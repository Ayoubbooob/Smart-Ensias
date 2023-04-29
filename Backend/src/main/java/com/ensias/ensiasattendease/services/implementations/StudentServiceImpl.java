package com.ensias.ensiasattendease.services.implementations;

import java.io.Console;
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
            if(studentRepository.findByCNE(cne)==null){
                return false ;
            }
            studentRepository.deleteByCNE(cne);
            return true ;
        } catch (Exception e){
            return false ;
        }
    }

    @Override 
    public StudentModel getStudentByCNE(String cne){
        return studentRepository.findByCNE(cne);
    }

    @Override
    public Collection<AttendanceModel> getStudentAllAttendance(String cne){
        StudentModel student = studentRepository.findByCNE(cne);
        Collection<AttendanceModel> attendances = student.getAttendances();
        return attendances ;
    }

    @Override
    public StudentModel updateStudent(StudentModel student){
        if(studentRepository.findByCNE(student.getCNE())==null){
            return null ;
        }
        return studentRepository.save(student);
    }

    @Override
    public AttendanceModel updateStudentAttendance(AttendanceModel attendance , String cne){
        StudentModel student = studentRepository.findByCNE(cne);
        if(student==null){
            return null ;
        }
        student.getAttendances().add(attendance);
        return attendanceRepository.save(attendance);
    }

    @Override
    public Boolean deleteStudentAttendance(String cne , Long id){
        StudentModel student = studentRepository.findByCNE(cne);
        if(student==null){
            return false ;
        }
        try {
            attendanceRepository.deleteById(id);
            return true ;
        } catch (Exception e) {
            return false ;
        }
    }

    
    
}
