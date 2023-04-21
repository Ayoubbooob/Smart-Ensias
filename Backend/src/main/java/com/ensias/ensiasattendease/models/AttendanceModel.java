package com.ensias.ensiasattendease.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private  Long id ; 
    private Date  dateTime ; 
    private String justification   ;
    @Enumerated(EnumType.ORDINAL) 
    private AttendanceStatus status ;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course  ; 
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<StudentModel> student = new ArrayList() ; 
}
