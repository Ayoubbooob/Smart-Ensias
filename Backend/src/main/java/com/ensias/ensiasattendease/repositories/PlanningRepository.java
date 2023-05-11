package com.ensias.ensiasattendease.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensias.ensiasattendease.models.PlanningModel;

@Repository
public interface PlanningRepository  extends JpaRepository<PlanningModel , Long>{
    PlanningModel save(PlanningModel planning) ;
    List<PlanningModel>findAll();
    PlanningModel findById(long id);
    PlanningModel findByDay(LocalDate day);

    
}
