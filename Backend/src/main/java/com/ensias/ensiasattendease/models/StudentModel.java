package com.ensias.ensiasattendease.models;


import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentModel {

    private Long id ; 
    private String CNE ; 
    @OneToMany(mappedBy = "student" , fetch = FetchType.LAZY)
    private Collection<AttendanceModel> attendances = new ArrayList() ;
}
