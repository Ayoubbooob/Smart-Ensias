package com.ensias.ensiasattendease.models;


import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StudentModel extends UserModel {

    @Column(unique = true)
    private String cne;
    @ManyToMany(mappedBy = "student" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("student")
    private Collection<AttendanceModel> attendance = new ArrayList<>() ;
    @ManyToOne
    @JsonBackReference(value = "filiere-student")
    private  FiliereModel filiere ;


    @JsonIgnore
    private int numberOfAbsences = 0;

    public int getNumberOfAbsences(){
        int absences = 0;
        if(attendance == null){
            return 0;
        }
        for(AttendanceModel attendance : attendance){
            if(attendance.getStatus().equals(AttendanceStatus.ABSENT))  absences++ ;
        }
        return absences;
    }

    public void incrementAbsence(){
        numberOfAbsences++;
    }
}
