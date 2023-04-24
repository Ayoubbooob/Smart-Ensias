package com.ensias.ensiasattendease.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensias.ensiasattendease.models.StudentModel;

public interface StudentRepository extends JpaRepository<StudentModel , Long> {
    List<StudentModel> findAll() ;
    
}
