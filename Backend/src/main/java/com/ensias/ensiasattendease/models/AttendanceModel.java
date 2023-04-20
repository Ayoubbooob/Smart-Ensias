package com.ensias.ensiasattendease.models;

import java.sql.Date;

import com.ensias.ensiasattendease.resources.AttendanceStatus;

public class AttendanceModel {
    private  Long id ; 
    private Date  dateTime ; 
    private String justification   ; 
    private AttendanceStatus status ;
}
