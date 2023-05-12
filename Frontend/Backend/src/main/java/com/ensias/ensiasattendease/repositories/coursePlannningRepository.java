package com.ensias.ensiasattendease.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensias.ensiasattendease.models.CoursePlanningModel;

@Repository
public interface coursePlannningRepository extends JpaRepository<CoursePlanningModel , Long> {

    CoursePlanningModel save(CoursePlanningModel coursePlan) ; 
    List<CoursePlanningModel> findAll();
    
}
