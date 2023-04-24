package com.ensias.ensiasattendease.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.repositories.AttendanceRepository;
import com.ensias.ensiasattendease.services.AttendanceService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor 
@Slf4j
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepository attendanceRepository ; 
    
    @Autowired
    public void AttendanceServiceInplementation(AttendanceRepository attendanceRepository ){
        this.attendanceRepository = attendanceRepository ; 
    }

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
}
