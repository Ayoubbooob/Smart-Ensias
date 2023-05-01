package com.ensias.ensiasattendease.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.JustificationModel;
import com.ensias.ensiasattendease.repositories.AttendanceRepository;
import com.ensias.ensiasattendease.repositories.JustificationRepository;
import com.ensias.ensiasattendease.services.AttendanceService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor 
@Slf4j
@Transactional
public class AttendanceServiceImpl<justificationRepository> implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository ; 

    @Autowired
    private JustificationRepository justificationRepository ; 
    
    
    public void AttendanceServiceImpl(AttendanceRepository attendanceRepository  , JustificationRepository justificationRepository){
        this.attendanceRepository = attendanceRepository ;  
        this.justificationRepository  = justificationRepository ; 

    }

    @Override
    public List<AttendanceModel> getAllAttendance(int page , int size){
        return attendanceRepository.findAll(PageRequest.of(page, size , Sort.by("dateTime").descending())).getContent() ;
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


}
