package com.ensias.ensiasattendease.services.implementations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.GenreUser;
import com.ensias.ensiasattendease.models.JustificationModel;
import com.ensias.ensiasattendease.repositories.AttendanceRepository;
import com.ensias.ensiasattendease.repositories.JustificationRepository;
import com.ensias.ensiasattendease.repositories.StudentRepository;
import com.ensias.ensiasattendease.services.AttendanceService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor 
@Slf4j
@Transactional
public class AttendanceServiceImpl<justificationRepository> implements AttendanceService {

    private final AttendanceRepository attendanceRepository ;

    private final StudentRepository studentRepository ;


    private final JustificationRepository justificationRepository ;


    
//    public void AttendanceServiceImpl(AttendanceRepository attendanceRepository  , JustificationRepository justificationRepository){
//        this.attendanceRepository = attendanceRepository ;
//        this.justificationRepository  = justificationRepository ;
//
//    }

    @Override
    public List<AttendanceModel> getAllAttendance(){
        return attendanceRepository.findAll() ;
    }

    @Override
    public AttendanceModel saveAttendanceModel(AttendanceModel attendance ){
        return attendanceRepository.save(attendance) ;
    }

    @Override
    public Optional<AttendanceModel>getAttendanceById(Long id){
        return attendanceRepository.findById(id);
    }

    @Override 
    public Boolean deleteAttendance(Long id){
        try {
            if(attendanceRepository.findById(id).isPresent() == false){
                return false ; 
            }
            attendanceRepository.deleteById(id);
            return true ;
        } catch (Exception e) {
            return false ;
        }
    }

    @Override
    public AttendanceModel addJustificationToAttendance(Long id , JustificationModel justification){ 
        if(attendanceRepository.findById(id).isPresent() == false){
            return null ; 
        }
        AttendanceModel attendance = attendanceRepository.findById(id).get();
        justification.getAttendance().add(attendance);
        justificationRepository.save(justification);
        attendance.setJustification(justification);
        return attendanceRepository.save(attendance) ;

    }

    @Override
    public JustificationModel getAttendanceJustification(Long id){
        if(attendanceRepository.findById(id).isPresent() == false){
            return null ; 
        }
        AttendanceModel attendance = attendanceRepository.findById(id).get();
        return attendance.getJustification() ;
    }

    @Override
    public AttendanceModel updateAttendance(AttendanceModel attendance){
        if(attendanceRepository.findById(attendance.getId()).isPresent() == false){
            return null ; 
        }
        return attendanceRepository.save(attendance) ; 
    }

    @Override
    public JustificationModel updateAttendanceJustification(JustificationModel justification ,  Long id ){
        if(attendanceRepository.findById(id).isPresent() == false){
            return null ; 
        }
        AttendanceModel attendance = attendanceRepository.findById(id).get();
        justificationRepository.save(justification);
        attendance.setJustification(justification);
        return justificationRepository.save(justification) ; 
    }

    @Override
    public Boolean deleteAttendanceJustification(Long id , Long idJustfication){
        if(attendanceRepository.findById(id).isPresent() == false){
            return false ; 
        }
        try {
            if(attendanceRepository.findById(id).get().getJustification() == null){
                return false ; 
            }
            attendanceRepository.findById(id).get().setJustification(null);
            justificationRepository.findById(idJustfication).get().getAttendance().remove(attendanceRepository.findById(id).get());
            return true ;
        } catch (Exception e) {
            // TODO: handle exception
            return false ;
        }
    }

    @Override
    public Long getCurentDayAttendanceNumber() {
        // attendanceRepository.findAll().forEach(attendance -> {
        //     if(attendance.getTakedTime().toLocalDate().equals(LocalDate.now()) == true){
        //         countedValue ++ ;
        //     }
        // });
        return attendanceRepository.findAll().stream().filter(attendance -> attendance.getDate().equals(LocalDate.now())).count() ;
        // TODO Auto-generated method stub
    }

    @Override
    public Long getCurentDayAttendanceBoysNumber() {
        return studentRepository.findAll().stream().filter(student -> student.getGenre().equals(GenreUser.MALE)).filter(student -> student.getAttendance().stream().filter(attendance -> attendance.getDate().equals(LocalDate.now())).count() > 0).count();
    }

    @Override
    public Long getCurentDayAttendanceGirlsNumber() {
        return studentRepository.findAll().stream().filter(student -> student.getGenre().equals(GenreUser.FEMALE)).filter(student -> student.getAttendance().stream().filter(attendance -> attendance.getDate().equals(LocalDate.now())).count() > 0).count();
    }

    @Override
    public Long getCurentDayAttendanceAbsencesNumber() {
        return studentRepository.findAll().stream().filter(student -> student.getAttendance().stream().filter(attendance -> attendance.getDate().equals(LocalDate.now())).count() == 0).count();
    }

    @Override
    public Long getYearAttendaceGirls(int month) {
        Long countedValue = studentRepository.findAll().stream().filter(student -> student.getGenre().equals(GenreUser.FEMALE)).filter(student -> student.getAttendance().stream().filter(attendance -> (attendance.getDate().getYear() == (LocalDate.now().getYear())) && (attendance.getDate().getMonthValue() == month )).count() > 0).count();
        return countedValue;
    }

    @Override
    public Long getYearAttendaceBoys(int month) {
        Long countedValue = studentRepository.findAll().stream().filter(student -> student.getGenre().equals(GenreUser.MALE)).filter(student -> student.getAttendance().stream().filter(attendance -> (attendance.getDate().getYear() == (LocalDate.now().getYear())) && (attendance.getDate().getMonthValue() == month )).count() > 0).count();
        return countedValue;
    }

    @Override
    public Long getYearAttendaceByClassGirls(Long filiereId) {
        Long countedValue = studentRepository.findAll().stream().filter(student -> student.getGenre().equals(GenreUser.FEMALE) && (student.getFiliere().getId() == filiereId)).filter(student -> student.getAttendance().stream().filter(attendance -> (attendance.getDate().equals(LocalDate.now()))).count() > 0).count();
        return countedValue;
    }

    @Override
    public Long getYearAttendaceByClassBoys(Long filiereId) {
        Long countedValue = studentRepository.findAll().stream().filter(student -> student.getGenre().equals(GenreUser.MALE) && (student.getFiliere().getId() == filiereId)).filter(student -> student.getAttendance().stream().filter(attendance -> (attendance.getDate().equals(LocalDate.now()))).count() > 0).count();
        return countedValue;
    }


}
