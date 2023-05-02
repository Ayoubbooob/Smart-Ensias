package com.ensias.ensiasattendease.services.implementations;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.CourseModel;
import com.ensias.ensiasattendease.models.FiliereModel;
import com.ensias.ensiasattendease.repositories.CourseRepository;
import com.ensias.ensiasattendease.repositories.FiliereRepository;
import com.ensias.ensiasattendease.services.FiliereService;

@Service
public class FiliereServiceImpl implements FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private CourseRepository courseRepository ;

    public FiliereServiceImpl(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    @Override
    public List<FiliereModel> getAllFiliere(int page, int size) {
        return filiereRepository.findAll();
    }

    @Override
    public Collection<CourseModel> getAllFiliereCourse(Long filierId) {
       if(filiereRepository.findById(filierId) == null){
            return null ; 
       }
        return filiereRepository.findById(filierId).get().getCourse() ;
    }

    @Override
    public CourseModel addCourseToFilier(Long filierId, CourseModel course) {
        FiliereModel filiere = filiereRepository.findById(filierId).get() ; 
        if(filiere == null){
            return null ; 
        }
        filiere.getCourse().add(course) ; 
        course.getFiliere().add(filiere);
        filiereRepository.save(filiere) ;
        
        return courseRepository.save(course)  ;
    }

    @Override
    public FiliereModel addFilier(FiliereModel filiere) {
        try {
            return filiereRepository.save(filiere);
        } catch (Exception e) {
            // TODO: handle exception
            return null ;
        }
    }

    
    
}
