package com.ensias.ensiasattendease.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CoursePlanning")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursePlanningModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startedDate ; 
    private LocalDateTime endedDate ;
    @ManyToOne()
    @JsonBackReference(value = "Courseplanning-course")
    private CourseModel courses ;
    @ManyToOne()
    @JsonBackReference(value = "Courseplanning-class")
    private classModel classe ;


    
}
