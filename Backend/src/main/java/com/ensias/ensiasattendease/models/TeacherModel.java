package com.ensias.ensiasattendease.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Teacher")
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherModel extends UserModel{
    @Column(unique = true)
    private String matricule;

    private LocalDate joining_date;
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnoreProperties("teacher")
    private Collection<FiliereModel> filiere ;

//AYOUB ADDED THIS
//    @ManyToMany(mappedBy = "teacher" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("teacher")
    //UNTIL HERE
  // THIS FOR OUMAR
    @OneToMany(mappedBy = "teacher" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonManagedReference(value = "attendance-teacher")
    private Collection<AttendanceModel> attendance = new ArrayList<>() ;


    @ManyToMany(mappedBy = "teacher" , fetch = FetchType.LAZY)
    @JsonIgnoreProperties("teacher")
    private Collection<CourseModel> course ;
}

