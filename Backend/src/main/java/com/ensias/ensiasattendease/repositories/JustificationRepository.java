package com.ensias.ensiasattendease.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensias.ensiasattendease.models.JustificationModel;

public interface JustificationRepository extends JpaRepository<JustificationModel , Long> {

    List<JustificationModel> findAll() ; 

    JustificationModel save(JustificationModel justification);

    void delete(JustificationModel justification);
    
}
