package com.ensias.ensiasattendease.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensias.ensiasattendease.models.ClasseModel;

@Repository
public interface ClasseRepository extends JpaRepository<ClasseModel , Long> {

    ClasseModel save(ClasseModel classe) ;
    List<ClasseModel> findAll() ;
    
}
