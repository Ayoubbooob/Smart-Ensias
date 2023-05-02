package com.ensias.ensiasattendease.models;

import java.util.Collection;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private String Matricule ;
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnoreProperties("teacher")
    private Collection<FiliereModel> filiere ;
    // @ManyToMany(mappedBy = "teacher" , fetch = FetchType.LAZY)
    // @JsonIgnoreProperties("teacher")
    // private Collection<CourseModel> course ;
}

