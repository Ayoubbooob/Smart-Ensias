package com.ensias.ensiasattendease.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Enumerated(EnumType.STRING) 
    private AttendanceStatus status = AttendanceStatus.ABSENT ;
    @Column(name = "started " , columnDefinition = "TIMESTAMP")
    private LocalDateTime  started = LocalDateTime.now() ;
    @Column(name="ended" , columnDefinition = "TIMESTAMP")
    private LocalDateTime  ended = LocalDateTime.now()  ;
    @Column(name ="takedTime" ,  columnDefinition = "TIMESTAMP")
    private LocalDateTime  takedTime = LocalDateTime.now()  ; 
    @ManyToOne
    @JsonBackReference(value="attendance-class")
    private classModel classe ;
    @ManyToOne
    @JsonBackReference(value="attendance-course")
    private CourseModel course  ; 
    @ManyToOne()
    @JsonBackReference(value = "attendance-student")
    private StudentModel student  ; 
    @ManyToOne()
    @JsonBackReference(value = "attendance-teacher")
    private TeacherModel teacher  ; 
    @ManyToOne()
    @JsonBackReference(value = "attendance-filiere")
    private FiliereModel filiere  ; 
    @ManyToOne
    @JsonBackReference(value="attendance-justification")
    private JustificationModel justification ; 
}
