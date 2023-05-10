package com.ensias.ensiasattendease.models;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.ManyToAny;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Filiere")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiliereModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ; 
    private String description ; 
    private String code ;
    @ManyToMany(mappedBy = "filiere" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnoreProperties("filiere")
    private Collection<TeacherModel> teacher = new ArrayList<>() ;
    @ManyToMany(mappedBy = "filiere" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnoreProperties("filiere")
    private Collection<CourseModel> course = new ArrayList<>() ;
    @OneToMany(mappedBy = "filiere" , fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JsonManagedReference(value = "filiere-student")
    private Collection<StudentModel> student = new ArrayList<>() ;
    @ManyToOne
    @JsonBackReference(value = "filiere-planning")
    private PlanningModel planning ;
    // @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL )
    // @JsonIgnoreProperties("filiere")
    // private Collection<PromotionModel> promotion ;

}
