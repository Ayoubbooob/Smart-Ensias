package com.ensias.ensiasattendease.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensias.ensiasattendease.models.JustificationModel;

@Repository
public interface JustificationRepository extends JpaRepository<JustificationModel , Long> {

    List<JustificationModel> findAll() ; 

    JustificationModel save(JustificationModel justification);

    void delete(JustificationModel justification);

    JustificationModel findById(long id);

    void deleteById(long id);
    
}
