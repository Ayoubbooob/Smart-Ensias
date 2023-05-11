package com.ensias.ensiasattendease.services;

import java.util.List;

import com.ensias.ensiasattendease.models.CourseModel;

public interface CourseService {
    List<CourseModel> getAllCourses(int page , int size);
    CourseModel saveCourseModel(CourseModel course, Long[] idFiliere);
    CourseModel getCourseById(Long id) ;
    Boolean deleteCourse(Long id);
    CourseModel updateCourse(CourseModel course);
}
