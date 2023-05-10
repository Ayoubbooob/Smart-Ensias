package com.ensias.ensiasattendease.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensias.ensiasattendease.models.FiliereModel;

@Repository
public interface FiliereRepository extends JpaRepository<FiliereModel,Long> {

    List<FiliereModel> findAll();
    void deleteById(Long id);
    void delete(FiliereModel filiere);


    FiliereModel findByName(String name);


}
