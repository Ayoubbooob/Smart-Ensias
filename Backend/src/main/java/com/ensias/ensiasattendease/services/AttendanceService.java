package com.ensias.ensiasattendease.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.models.JustificationModel;

public interface AttendanceService {
    List<AttendanceModel> getAllAttendance();

    Long getCurentDayAttendanceNumber();
    Long getCurentDayAttendanceBoysNumber();
    Long getCurentDayAttendanceGirlsNumber();
    Long getCurentDayAttendanceAbsencesNumber();
    Long getYearAttendaceGirls(int month);
    Long getYearAttendaceBoys(int month);
    Long getYearAttendaceByClassGirls(Long filiereId);
    Long getYearAttendaceByClassBoys(Long filiereId);
    AttendanceModel saveAttendanceModel(AttendanceModel attendance);
    Optional<AttendanceModel>getAttendanceById(Long id) ;
    Boolean deleteAttendance(Long id);
    AttendanceModel addJustificationToAttendance(Long id  , JustificationModel attendance);
    JustificationModel getAttendanceJustification(Long id) ;
    AttendanceModel updateAttendance(AttendanceModel attendance);
    JustificationModel updateAttendanceJustification(JustificationModel justification , Long id);
    Boolean deleteAttendanceJustification(Long id , Long idJustfication);
}
