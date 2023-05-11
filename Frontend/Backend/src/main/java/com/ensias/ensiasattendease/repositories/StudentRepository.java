package com.ensias.ensiasattendease.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ensias.ensiasattendease.models.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel , Long> {
    List<StudentModel> findAll() ;
    StudentModel findByCne(String cne) ;
//    CNE -- cne


    StudentModel save(StudentModel student);
    void delete(StudentModel student);
    void deleteByCne(String cne) ;




    StudentModel findByphone(String phone) ;
    @Query("SELECT s FROM StudentModel s WHERE s.id = :id")
    StudentModel findStudentById(@Param("id") Long id);//findById
}
