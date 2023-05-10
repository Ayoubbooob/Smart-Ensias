package com.ensias.ensiasattendease.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensias.ensiasattendease.models.AttendanceModel;
import com.ensias.ensiasattendease.services.AttendanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/olap")
@RequiredArgsConstructor
public class dashboarController {
    
    @Autowired
    private final AttendanceService attenService;

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard(){
        ObjectNode dashboard = new ObjectMapper().createObjectNode();
        dashboard.put("totalAttendance" , attenService.getCurentDayAttendanceNumber());
        dashboard.put("totalBoys" , attenService.getCurentDayAttendanceBoysNumber());
        dashboard.put("totalGirls" , attenService.getCurentDayAttendanceGirlsNumber());
        dashboard.put("totalAbsence" , attenService.getCurentDayAttendanceAbsencesNumber());
        return new ResponseEntity<>(dashboard , HttpStatus.OK);
    }
    @GetMapping("/overview")
    public ResponseEntity<?> getOverview(){
        ObjectNode dashboardOverview = new ObjectMapper().createObjectNode();
        dashboardOverview.put("year" , LocalDate.now().getYear());
        ArrayNode arrayNode =  dashboardOverview.putArray("attendance");
        for(int i = 1 ; i <= 12 ; i++){
            ObjectNode month = new ObjectMapper().createObjectNode();
            month.put("month" , i);
            month.put("totalGirls" , attenService.getYearAttendaceGirls(i));
            month.put("totalBoys" , attenService.getYearAttendaceBoys(i));
            arrayNode.add(month);
        }
        return new ResponseEntity<>(dashboardOverview , HttpStatus.OK);
    }
}
