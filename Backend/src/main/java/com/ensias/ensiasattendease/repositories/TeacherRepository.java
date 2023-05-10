package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.TeacherModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Long> {


    TeacherModel findByMatricule(String matricule);

}
