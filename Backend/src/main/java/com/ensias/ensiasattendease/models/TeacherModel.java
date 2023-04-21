package com.ensias.ensiasattendease.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Teacher")
@NoArgsConstructor
@AllArgsConstructor
public class TeacherModel extends UserModel{
    private String Matricule ;
}
