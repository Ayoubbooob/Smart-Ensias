package com.ensias.ensiasattendease.models;


import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentModel extends UserModel {
    private String CNE ; 
    @ManyToMany(mappedBy = "student" , fetch = FetchType.LAZY)
    private Collection<AttendanceModel> attendances  ;
}
