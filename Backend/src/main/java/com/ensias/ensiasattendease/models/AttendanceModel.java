package com.ensias.ensiasattendease.models;

import java.sql.Date;
import java.util.Collection;

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

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date  dateTime ; 
    @Enumerated(EnumType.STRING) 
    private AttendanceStatus status ;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course  ; 
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<StudentModel> student  ; 
    @ManyToOne
    private JustificationModel justification ; 
}
