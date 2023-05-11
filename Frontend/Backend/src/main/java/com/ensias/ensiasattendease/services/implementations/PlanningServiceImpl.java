package com.ensias.ensiasattendease.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.PlanningModel;
import com.ensias.ensiasattendease.repositories.PlanningRepository;
import com.ensias.ensiasattendease.services.PlanningService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlanningServiceImpl implements PlanningService {


    @Autowired
    private PlanningRepository planningRepository ;

    @Override
    public List<PlanningModel> getAllPlanning() {
        return planningRepository.findAll();
    }

    @Override
    public PlanningModel getPlanningById(long id) {
        PlanningModel plann =  planningRepository.findById(id);
        if(plann == null){
            return null ; 
        }
        return plann;
    }

    
    
}
