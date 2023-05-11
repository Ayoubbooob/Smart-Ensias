package com.ensias.ensiasattendease.services;

import java.util.List;

import com.ensias.ensiasattendease.models.ClasseModel;

public interface ClasseService {

    ClasseModel saveClasse(ClasseModel classe);
    List<ClasseModel> getAllClasses();
    ClasseModel getClasseById(Long id);
    Boolean deleteClasse(Long id);
    ClasseModel updateClasse(ClasseModel classe);
    
}
