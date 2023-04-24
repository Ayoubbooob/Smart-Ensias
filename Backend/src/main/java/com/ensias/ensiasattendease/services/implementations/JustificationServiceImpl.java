package com.ensias.ensiasattendease.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.JustificationModel;
import com.ensias.ensiasattendease.repositories.JustificationRepository;
import com.ensias.ensiasattendease.services.JustificationService;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class JustificationServiceImpl implements JustificationService {

    private JustificationRepository justificationRepository ; 

    @Autowired
    public JustificationServiceImpl(JustificationRepository justificationRepository){
        this.justificationRepository = justificationRepository ; 
    }

    @Override
    public List<JustificationModel> getAllJustification(){
        return justificationRepository.findAll();
    }

    @Override
    public JustificationModel deleteJustification(Long id){
        JustificationModel justificationD  = justificationRepository.findById(id).get() ; 
        justificationRepository.delete(justificationRepository.findById(id).get()) ; 
        return justificationD ; 
    }

    @Override
    public JustificationModel createJustification(JustificationModel justification){
        return justificationRepository.save(justification);
    }

    // @Override
    // public Boolean updateAllJustification(JustificationModel justification){
    //     try {
    //         justificationRepository.findAllById(justification.getId());
    //         return true ;
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         log.error(e.getMessage());
    //         return false ;
    //     }
    // }
    
}
