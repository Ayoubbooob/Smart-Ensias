package com.ensias.ensiasattendease.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensias.ensiasattendease.models.AttendanceModel;

public interface AttendanceRepository extends JpaRepository<AttendanceModel , Long> {
    
}
