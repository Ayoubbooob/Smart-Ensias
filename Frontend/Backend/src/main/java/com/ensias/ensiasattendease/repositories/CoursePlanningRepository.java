package com.ensias.ensiasattendease.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensias.ensiasattendease.models.CoursePlanningModel;

public interface CoursePlanningRepository extends JpaRepository<CoursePlanningModel, Long> {

    CoursePlanningModel save(CoursePlanningRepository coursePlanningRepository);
    List<CoursePlanningModel> findAll();
    
}
