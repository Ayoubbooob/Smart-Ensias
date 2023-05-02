package com.ensias.ensiasattendease.models;


import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Student extends User {
    private String cne;

    @ManyToMany(mappedBy = "student" , fetch = FetchType.LAZY)
    private Collection<AttendanceModel> attendances  ;


}
