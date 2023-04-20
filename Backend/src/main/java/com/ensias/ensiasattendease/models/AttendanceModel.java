package com.ensias.ensiasattendease.models;

import java.sql.Date;

import org.hibernate.annotations.ManyToAny;

import com.ensias.ensiasattendease.resources.AttendanceStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private  Long id ; 
    private Date  dateTime ; 
    private String justification   ; 
    private AttendanceStatus status ;
    private CourseModel course  ; 
    @ManyToAny
    @JoinColumn(name = "student_id" , nullable = false)
    private StudentModel student ; 
}
