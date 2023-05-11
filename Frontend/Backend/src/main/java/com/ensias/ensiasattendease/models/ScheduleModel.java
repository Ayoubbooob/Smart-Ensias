package com.ensias.ensiasattendease.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Schedule")
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ; 
}
