package com.ensias.ensiasattendease.models;

import java.time.LocalDate;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    // @ManyToMany(mappedBy = "teacher" , fetch = FetchType.LAZY)
    // @JsonIgnoreProperties("teacher")
    // private Collection<CourseModel> course ;
}

