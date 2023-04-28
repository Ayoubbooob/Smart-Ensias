package com.ensias.ensiasattendease.services;

import java.util.List;
import java.util.Optional;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.JustificationModel;

public interface JustificationService {

    List<JustificationModel> getAllJustification() ;

    JustificationModel createJustification(Long attendanceId ,JustificationModel justification) ;

    JustificationModel deleteJustification(Long id) ;

    JustificationModel addAttendance(AttendanceModel attendance);

    JustificationModel affectAttendanceTOJustification(Long id  , AttendanceModel attendance);


    // Boolean updateAllJustification(JustificationModel justification) ;


    
}
