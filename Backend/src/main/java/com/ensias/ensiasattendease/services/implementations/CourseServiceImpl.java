package com.ensias.ensiasattendease.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.CourseModel;
import com.ensias.ensiasattendease.repositories.CourseRepository;
import com.ensias.ensiasattendease.services.CourseService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository coursRepository;

    public CourseServiceImpl(CourseRepository coursRepository){
        this.coursRepository = coursRepository;
    }

    @Override
    public List<CourseModel> getAllCourses(int page, int size) {
        // TODO Auto-generated method stub
        return coursRepository.findAll(PageRequest.of(page, size)).getContent();
       
    }

    @Override
    public CourseModel saveCourseModel(CourseModel course) {
        // TODO Auto-generated method stub
        return coursRepository.save(course);
    }

    @Override
    public CourseModel getCourseById(Long id) {
        // TODO Auto-generated method stub
        CourseModel course =  coursRepository.findById(id).get();
        if(course == null){
            return null;
        }
        return course;
    }

    @Override
    public Boolean deleteCourse(Long id) {
       try {
        coursRepository.deleteById(id);
        return true;
       } catch (Exception e) {
        // TODO: handle exception
        return false;
       }
    }

    @Override
    public CourseModel updateCourse(CourseModel course) {
        try {
            coursRepository.save(course);
            return course;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    
    
}
