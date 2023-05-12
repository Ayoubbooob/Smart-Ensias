package com.ensias.ensiasattendease.services;

import java.util.List;

import com.ensias.ensiasattendease.models.PlanningModel;

public interface PlanningService {

    List<PlanningModel> getAllPlanning();   
    PlanningModel getPlanningById(long id); 
}
