package com.ensias.ensiasattendease.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensias.ensiasattendease.models.AttendanceModel;
import java.time.LocalDateTime;


@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceModel , Long> {

    List<AttendanceModel> findAll() ;
    AttendanceModel save(AttendanceModel attendance) ;
    List<AttendanceModel> findByTakedTime(LocalDateTime takedTime) ;
    AttendanceModel findByStudentId(Long id) ;
    void delete (AttendanceModel attendance) ;
    void deleteByStudentId(Long id) ;
    
}
