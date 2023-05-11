package com.ensias.ensiasattendease.services;

import java.util.Collection;
import java.util.List;

import com.ensias.ensiasattendease.models.CourseModel;
import com.ensias.ensiasattendease.models.FiliereModel;
import com.ensias.ensiasattendease.models.PlanningModel;
import com.fasterxml.jackson.databind.JsonNode;

public interface FiliereService {
    List<FiliereModel> getAllFiliere();
    Collection<CourseModel> getAllFiliereCourse(Long filierId);
    CourseModel addCourseToFilier(Long filierId , CourseModel course);
    PlanningModel addPlanningToFilier(JsonNode node , Long filierId);
    FiliereModel addFilier(FiliereModel filiere);
    Boolean deleteFiliere(Long FiliereId) ;
    PlanningModel deserilizeAddPlanningToFilierBody(JsonNode node) ; 

}
