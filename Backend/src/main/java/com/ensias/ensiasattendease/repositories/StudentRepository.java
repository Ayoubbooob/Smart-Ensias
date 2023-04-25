package com.ensias.ensiasattendease.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensias.ensiasattendease.models.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel , Long> {
    List<StudentModel> findAll() ;
    StudentModel findByCNE(String cne) ;
    StudentModel save(StudentModel student);
    void delete(StudentModel student);
    void deleteByCNE(String cne) ;
    
}
