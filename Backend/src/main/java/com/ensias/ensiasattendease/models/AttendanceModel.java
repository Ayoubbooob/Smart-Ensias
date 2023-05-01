package com.ensias.ensiasattendease.models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "Attendance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AttendanceModel  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private  Long id ; 
    private LocalDateTime  starting  = java.time.LocalDateTime.now()  ; 
    private LocalDateTime  ending  = java.time.LocalDateTime.now()  ; 
    private LocalDateTime  taked  = java.time.LocalDateTime.now()  ; 
    @Enumerated(EnumType.STRING) 
    private AttendanceStatus status = AttendanceStatus.ABSENT ;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course  ; 
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("attendance")
    private Collection<StudentModel> student = new ArrayList<>()  ; 
    @ManyToOne
    @JsonBackReference
    private JustificationModel justification ; 
}
