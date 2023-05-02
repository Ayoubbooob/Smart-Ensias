package com.ensias.ensiasattendease.services.implementations;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.AttendanceStatus;
import com.ensias.ensiasattendease.models.FiliereModel;
import com.ensias.ensiasattendease.models.StudentModel;
import com.ensias.ensiasattendease.repositories.AttendanceRepository;
import com.ensias.ensiasattendease.repositories.FiliereRepository;
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

    @Autowired
    private FiliereRepository filiereRepository ;


    public AttendanceModel saveAttendance(AttendanceModel attendance){
        return attendanceRepository.save(attendance);
    }
    public StudentServiceImpl(StudentRepository studentRepository , AttendanceRepository attendanceRepository ){
        this.studentRepository = studentRepository ; 
        this.attendanceRepository = attendanceRepository ;
    }

    @Override
    public List<StudentModel> getAllStudent(){
        return studentRepository.findAll();
    }

    @Override
    public StudentModel enrollStudent(StudentModel student){
        try {
            if(studentRepository.findByphone(student.getPhone() )!=null){
                return null ;
            }
            if(studentRepository.findByCNE(student.getCNE())!=null){
                return null ;
            }
            return studentRepository.save(student);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return null ;
        }
    }

    /**
     * @target this methode is used to register student attendance 
     */
    @Override
    public AttendanceModel registerAttendance(AttendanceModel attendance , String cne){
        try {
            
            StudentModel student = studentRepository.findByCNE(cne);
            if(student == null){
               return null ;
            }
            attendance.getStudent().add(student);
            student.getAttendance().add(attendance);
            return attendanceRepository.save(attendance) ;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return null ;
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
        if(studentRepository.findByCNE(cne)==null){
            return null ;
        }
        Collection<AttendanceModel> attendances = studentRepository.findByCNE(cne).getAttendance();
        if(attendances == null){
            return null ;
        }
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
        student.getAttendance().forEach(
            (AttendanceModel att)->{
                if(att.getId()==attendance.getId()){
                    att.setStatus(attendance.getStatus());
                    attendanceRepository.save(att);
                }
            }
        );
        attendanceRepository.findById(attendance.getId()).get().setStatus(attendance.getStatus());
        studentRepository.save(student);
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

    @Override
    public FiliereModel getStudentFiliere(String cne) {
        if(studentRepository.findByCNE(cne) == null){
            return null ;
        }
        FiliereModel filiere =  studentRepository.findByCNE(cne).getFiliere();
        return filiere ;
    }
    @Override
    public FiliereModel affectStudentFiliere(String cne, Long id) {
        StudentModel student = studentRepository.findByCNE(cne);
        if(student==null || filiereRepository.findById(id).isPresent() == false){
            return null ;
        }
        FiliereModel filiere = filiereRepository.findById(id).get();
        student.setFiliere(filiere);
        filiere.getStudent().add(student);
        filiereRepository.save(filiere);
        return studentRepository.save(student).getFiliere();
    }
    @Override
    public FiliereModel updateStudentFiliere(String cne, FiliereModel filiere) {
        StudentModel student = studentRepository.findByCNE(cne);
        if(student==null){
            return null ;
        }
        filiere.getStudent().forEach(
            (StudentModel std)->{
                if(std.getCNE().equals(cne)){
                    std.setFiliere(filiere);
                    studentRepository.save(std);
                }
            }
        );
        student.setFiliere(filiere);
        filiereRepository.save(filiere);
        return studentRepository.save(student).getFiliere();
    }
    @Override
    public Boolean deleteStudentFiliere(String cne) {
        StudentModel student = studentRepository.findByCNE(cne);
        if(student==null){
            return false ;
        }
        filiereRepository.delete(student.getFiliere());
        return  true  ;
    }

    

    
    
}
