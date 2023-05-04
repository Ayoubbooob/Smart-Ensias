package com.ensias.ensiasattendease.services;

import java.util.List;
import java.util.Optional;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.JustificationModel;

public interface JustificationService {

    List<JustificationModel> getAllJustification(int page , int size) ;

    JustificationModel createJustification(JustificationModel justification) ;

    JustificationModel deleteJustification(Long id) ;

    JustificationModel addAttendance(AttendanceModel attendance);

    JustificationModel affectAttendanceTOJustification(Long id  , AttendanceModel attendance);

    JustificationModel getJustificationById(Long id) ;

    JustificationModel updateJustification(JustificationModel justification) ;


    // Boolean updateAllJustification(JustificationModel justification) ;


    
}
