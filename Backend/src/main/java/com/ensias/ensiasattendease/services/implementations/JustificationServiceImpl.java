package com.ensias.ensiasattendease.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.JustificationModel;
import com.ensias.ensiasattendease.repositories.AttendanceRepository;
import com.ensias.ensiasattendease.repositories.JustificationRepository;
import com.ensias.ensiasattendease.services.JustificationService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class JustificationServiceImpl implements JustificationService {

    @Autowired
    private JustificationRepository justificationRepository ; 
    @Autowired
    private AttendanceRepository attendanceRepository ; 

   
    public JustificationServiceImpl(JustificationRepository justificationRepository){
        this.justificationRepository = justificationRepository ; 
    }

    @Override
    public List<JustificationModel> getAllJustification(){
        return justificationRepository.findAll();
    }

    @Override
    public JustificationModel deleteJustification(Long id){
        JustificationModel justificationD  = justificationRepository.findById(id).get() ; 
        if(justificationD == null){
            return null ; 
        }
        justificationRepository.delete(justificationRepository.findById(id).get()) ; 
        return justificationD ; 
    }

    @Override
    public JustificationModel createJustification(Long attendanceId ,JustificationModel justification){
        Optional<AttendanceModel> attendance  = attendanceRepository.findById(attendanceId);
        if(attendance.isEmpty()){
            return null ; 
        }
        justification.getAttendance().add(attendance.get());
        return justificationRepository.save(justification);
    }

    @Override 
    public JustificationModel addAttendance(AttendanceModel attendance){
        JustificationModel justification = new JustificationModel() ; 
        justification.getAttendance().add(attendance);
        return justificationRepository.save(justification);
    }

    @Override 
    public JustificationModel affectAttendanceTOJustification(Long id , AttendanceModel attendance){
        JustificationModel justification = justificationRepository.findById(id).get() ; 
        if(justification == null){
            return null ; 
        }
        justification.getAttendance().add(attendance);
        return justificationRepository.save(justification);
    }

    @Override
    public JustificationModel getJustificationById(Long id){
        if(justificationRepository.findById(id).isEmpty()){
            return null ; 
        }
        return justificationRepository.findById(id).get() ; 
    }

    @Override
    public JustificationModel updateJustification(JustificationModel justification){
        if(justificationRepository.findById(justification.getId()).isEmpty()){
            return null ; 
        }
        return justificationRepository.save(justification);
    }


    // @Override
    // public Boolean updateAllJustification(JustificationModel justification){
    //     try {
    //         justificationRepository.findAllById(justification.getId());
    //         return true ;
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         log.error(e.getMessage());
    //         return false ;
    //     }
    // }
    
}
