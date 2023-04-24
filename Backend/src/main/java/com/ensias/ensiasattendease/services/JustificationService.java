package com.ensias.ensiasattendease.services;

import java.util.List;

import com.ensias.ensiasattendease.models.JustificationModel;

public interface JustificationService {

    List<JustificationModel> getAllJustification() ;

    JustificationModel createJustification(JustificationModel justification) ;

    JustificationModel deleteJustification(Long id) ;

    // Boolean updateAllJustification(JustificationModel justification) ;


    
}
