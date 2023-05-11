package com.ensias.ensiasattendease.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.ClasseModel;
import com.ensias.ensiasattendease.repositories.ClasseRepository;
import com.ensias.ensiasattendease.services.ClasseService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClasseServiceImpl implements ClasseService{

    @Autowired 
    private ClasseRepository classeRepository ;

    @Override
    public ClasseModel saveClasse(ClasseModel classe) {
       return classeRepository.save(classe);
    }

    @Override
    public List<ClasseModel> getAllClasses() {
        return classeRepository.findAll();
    }

    @Override
    public ClasseModel getClasseById(Long id) {
        ClasseModel classe = classeRepository.findById(id).orElse(null);
        if(classe == null){
            return null ;
        }
        return classe;
    }

    @Override
    public Boolean deleteClasse(Long id) {
        ClasseModel classe = classeRepository.findById(id).orElse(null);
        if(classe == null){
            return false ;
        }
        classeRepository.delete(classe);
        return true ;
    }

    @Override
    public ClasseModel updateClasse(ClasseModel classe) {
        ClasseModel classeToUpdate = classeRepository.findById(classe.getId()).orElse(null);
        if(classeToUpdate == null){
            return null ;
        }
        classeToUpdate.setCode(classe.getCode());
        return classeRepository.save(classeToUpdate);
    }
    
}
