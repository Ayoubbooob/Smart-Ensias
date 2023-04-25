package com.ensias.ensiasattendease.services;

import java.util.List;
import java.util.Optional;

import com.ensias.ensiasattendease.models.AttendanceModel;

public interface AttendanceService {
    List<AttendanceModel> getAllAttendance();
    AttendanceModel saveAttendanceModel(AttendanceModel attendance);
    Optional<AttendanceModel>getAttendanceById(Long id) ;
    Boolean deleteAttendance(Long id);
}
