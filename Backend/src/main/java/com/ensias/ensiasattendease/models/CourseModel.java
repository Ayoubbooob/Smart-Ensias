package com.ensias.ensiasattendease.models;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ; 
    private String name ; 
    private String description ; 
    private String code  ; 
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("course")
    private Collection<FiliereModel> filiere = new ArrayList<>() ;
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<FiliereModel> teacher;
}
