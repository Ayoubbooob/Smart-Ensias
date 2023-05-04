package com.ensias.ensiasattendease.services;

import java.util.Collection;
import java.util.List;

import com.ensias.ensiasattendease.models.CourseModel;
import com.ensias.ensiasattendease.models.FiliereModel;

public interface FiliereService {
    List<FiliereModel> getAllFiliere(int page, int size);
    Collection<CourseModel> getAllFiliereCourse(Long filierId);
    CourseModel addCourseToFilier(Long filierId , CourseModel course);
    FiliereModel addFilier(FiliereModel filiere);

}
