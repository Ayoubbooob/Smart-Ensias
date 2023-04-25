package com.ensias.ensiasattendease.models;


import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
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
    private Collection<AttendanceModel> attendances  ;
}
