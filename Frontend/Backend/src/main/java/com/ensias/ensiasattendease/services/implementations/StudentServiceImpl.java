package com.ensias.ensiasattendease.services.implementations;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import com.ensias.ensiasattendease.models.*;
import com.ensias.ensiasattendease.repositories.UserRepository;
import com.ensias.ensiasattendease.resources.RequestModels.StudentRegisterRequest;
import com.ensias.ensiasattendease.resources.responses.StudentResponse;
import com.ensias.ensiasattendease.services.StudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.repositories.AttendanceRepository;
import com.ensias.ensiasattendease.repositories.CourseRepository;
import com.ensias.ensiasattendease.repositories.FiliereRepository;
import com.ensias.ensiasattendease.repositories.PlanningRepository;
import com.ensias.ensiasattendease.repositories.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

   
    private final StudentRepository studentRepository ;

    
    private final  UserRepository userRepository;

    private  final FiliereRepository filiereRepository ;

    private final AttendanceRepository attendanceRepository ;
   
    private  final PasswordEncoder passwordEncoder;

    private final PlanningRepository planningRepository ;

    private final CourseRepository courseRepository ;

    public AttendanceModel saveAttendance(AttendanceModel attendance){
        return attendanceRepository.save(attendance);
    }


    @Override
    public List<StudentModel> getAllStudent(){
        return studentRepository.findAll();
    }

    @Override
    public StudentModel enrollStudent(StudentModel student){
        try {
            if(studentRepository.findByphone(student.getPhone())!=null){
                return null ;
            }
            if(studentRepository.findByCne(student.getCne()) !=null){
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
    public AttendanceModel registerAttendance(AttendanceStatus status , Long course_id , String cne){
        try {
            
            StudentModel student = studentRepository.findByCne(cne);
            if(student == null){
                System.out.println("student not found"); 
                return null ;
            }
            PlanningModel planning = planningRepository.findAll().stream().filter(p -> p.getFiliere().stream().filter(fil -> fil.getName().equals(student.getFiliere().getName())).count() > 0).findFirst().orElse(null);
            if(planning == null){
                System.err.println("planning not found");
                return null ;
            }
            CoursePlanningModel coursePlan = planning.getCoursePlanning().stream().filter(c -> c.getCourses().getId() == course_id).findFirst().orElse(null);
            if(coursePlan == null){
                System.err.println(planning.getCoursePlanning());
                return null ;
            }
            else if(coursePlan.getStartedTime().isAfter(LocalTime.now()) || coursePlan.getEndedTime().isBefore(LocalTime.now())){
                System.err.println("course not started yet");
                return null ;
            }
//             attendance.getStudent().add(student); //FOR AYOUB
          
            AttendanceModel attendance = new AttendanceModel(); 
            CourseModel courseFounded = courseRepository.findById(course_id).get();
            attendance.setCourse(courseFounded);
            attendance.setDate(LocalDate.now());
            attendance.setStarted(coursePlan.getStartedTime());
            attendance.setEnded(coursePlan.getEndedTime());
            attendance.setFiliere(student.getFiliere());
            attendance.setStatus(status);
            attendance.setStudent(student);
            attendance.setClasse(coursePlan.getClasse());
            student.getAttendance().add(attendance);
            /**
             * @target increment student absence if he is absent
             * @Ayoub
             */
            // if(attendance.getStatus().equals(AttendanceStatus.ABSENT)) student.incrementAbsence();
            attendance.setStudent(student);
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
            if(studentRepository.findByCne(cne)==null){
                return false ;
            }
            studentRepository.deleteByCne(cne);
            return true ;
        } catch (Exception e){
            return false ;
        }
    }

    @Override 
    public StudentModel getStudentByCNE(String cne){
        return studentRepository.findByCne(cne);
    }

    @Override
    public Collection<AttendanceModel> getStudentAllAttendance(String cne){
        if(studentRepository.findByCne(cne)==null){
            return null ;
        }
        Collection<AttendanceModel> attendances = studentRepository.findByCne(cne).getAttendance();
        if(attendances == null){
            return null ;
        }
        return attendances ;
    }

    @Override
    public StudentModel updateStudent(StudentModel student){
        StudentModel studentModel = studentRepository.findByCne(student.getCne());
        if(studentModel == null){
            return null ;
        }
        return studentRepository.save(student);
    }

    @Override
    public AttendanceModel updateStudentAttendance(AttendanceModel attendance , String cne){
        StudentModel student = studentRepository.findByCne(cne);
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
        StudentModel student = studentRepository.findByCne(cne);
        if(student==null){
            return false ;
        }
        try {
            AttendanceModel attendanceModel = attendanceRepository.findById(id).get();
            if(attendanceModel == null){
                return false;
            }
            attendanceRepository.deleteById(id);
            return true ;
        } catch (Exception e) {
            return false ;
        }
    }

    @Override
    public FiliereModel getStudentFiliere(String cne) {
        if(studentRepository.findByCne(cne) == null){
            return null ;
        }
        FiliereModel filiere =  studentRepository.findByCne(cne).getFiliere();
        return filiere ;
    }
    @Override
    public FiliereModel affectStudentFiliere(String cne, Long id) {
        StudentModel student = studentRepository.findByCne(cne);
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
        StudentModel student = studentRepository.findByCne(cne);
        if(student==null){
            return null ;
        }
        filiere.getStudent().forEach(
            (StudentModel std)->{
                if(std.getCne().equals(cne)){
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
        StudentModel student = studentRepository.findByCne(cne);
        if(student==null){
            return false ;
        }
        filiereRepository.delete(student.getFiliere());
        return  true  ;
    }

    public StudentModel registerStudent(StudentRegisterRequest request) {

        StudentModel student = StudentModel.builder()
                .first_name(request.getFirstname())
                .last_name(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
// .password(request.getPassword())

                .role(Role.STUDENT)
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

        UserModel user =  userRepository.findById(Long.valueOf(id)).get();
        StudentModel student = studentRepository.findStudentById(Long.valueOf(id));

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
