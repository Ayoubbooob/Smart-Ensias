package com.ensias.ensiasattendease.services.implementations;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.ensias.ensiasattendease.models.GenreUser;
import com.ensias.ensiasattendease.models.User;
import com.ensias.ensiasattendease.repositories.UserRepository;
import com.ensias.ensiasattendease.resources.RequestModels.StudentRegisterRequest;
import com.ensias.ensiasattendease.resources.responses.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.Student;
import com.ensias.ensiasattendease.repositories.AttendanceRepository;
import com.ensias.ensiasattendease.repositories.StudentRepository;
import com.ensias.ensiasattendease.services.StudentService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository ;

    private final UserRepository userRepository;

    private AttendanceRepository attendanceRepository ;

    private final PasswordEncoder passwordEncoder;
//    public StudentServiceImpl(StudentRepository studentRepository ){
//        this.studentRepository = studentRepository ;
//    }

    @Override
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    @Override
    public Student enrollStudent(Student student){
        return studentRepository.save(student);
    }

    /**
     * @target this methode is used to register student attendance 
     */
    @Override
    public Boolean registerAttendance(AttendanceModel attendance , String cne){
        try {
            Student student = studentRepository.findByCne(cne);
            AttendanceModel attendanceFounded = attendanceRepository.save(attendance);
            student.getAttendances().add(attendanceFounded);
            studentRepository.save(student);
            attendanceFounded.getStudent().add(student);
            attendanceRepository.save(attendanceFounded);
            return true ;
        } catch (Exception e) {
            // TODO: handle exception
            return false ;
        }

    }

    @Override
    public Boolean deleteStudent(String cne){
        try {
            studentRepository.deleteByCne(cne);
            return true ;
        } catch (Exception e){
            return false ;
        }
    }

    @Override 
    public Collection<AttendanceModel> getStudentAttendance(String cne){
        Student student = studentRepository.findByCne(cne);
        return student.getAttendances();
    }



    public Student registerStudent(StudentRegisterRequest request) {
        Student student = Student.builder()
                .first_name(request.getFirstname())
                .last_name(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))

//                .password(request.getPassword())

                .role("Student")
                .phone(request.getPhone())
                .image_url(request.getImage_url())
                .date_of_birth(LocalDate.parse(request.getDate_of_birth()))
                .genre(GenreUser.valueOf(request.getGender()))
                .cne(request.getCne())
                .build();

        System.out.println("******** : Student "+ student);
        studentRepository.save(student);
        return student;
    }


    public StudentResponse getStudentById(String id) {

        User user =  userRepository.findUserById(Long.valueOf(id));
        Student student = studentRepository.findStudentById(Long.valueOf(id));
        StudentResponse studentToReturn = StudentResponse.builder()
                .firstname(user.getFirst_name())
                .lastname(user.getLast_name())
                .cne(student.getCne())
                .date_of_birth(String.valueOf(user.getDate_of_birth()))
                .image_url(user.getImage_url())
                .gender(String.valueOf(user.getGenre()))
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();

        return studentToReturn;
    }
}
