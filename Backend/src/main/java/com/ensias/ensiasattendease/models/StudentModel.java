package com.ensias.ensiasattendease.models;


import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentModel extends UserModel {
    private String CNE ; 
    @ManyToMany(mappedBy = "student" , fetch = FetchType.LAZY)
    @JsonIgnoreProperties("student")
    private Collection<AttendanceModel> attendance = new ArrayList<>() ;
    @ManyToOne
    @JsonBackReference(value = "filiere-student")
    private  FiliereModel filiere ;
}
