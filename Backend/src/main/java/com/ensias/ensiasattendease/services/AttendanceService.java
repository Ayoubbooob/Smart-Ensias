package com.ensias.ensiasattendease.services;

import java.util.List;

import com.ensias.ensiasattendease.models.AttendanceModel;

public interface AttendanceService {
    List<AttendanceModel> getAllAttendance();
}
