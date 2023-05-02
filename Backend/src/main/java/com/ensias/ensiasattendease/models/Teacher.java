package com.ensias.ensiasattendease.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Teacher")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Teacher extends User {
    private String matricule ;


}
