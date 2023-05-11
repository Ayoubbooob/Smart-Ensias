package com.ensias.ensiasattendease.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "planning")
@Data
public class PlanningModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startedDate ; 
    private LocalDate endedDate ;
    private DaysOfWeek day;
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnoreProperties("planning")
    private Collection<CoursePlanningModel> coursePlanning = new ArrayList<>() ;
    @OneToMany(mappedBy = "planning" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonManagedReference(value = "filiere-planning")
    private Collection<FiliereModel> filiere = new ArrayList<>();
    
}
