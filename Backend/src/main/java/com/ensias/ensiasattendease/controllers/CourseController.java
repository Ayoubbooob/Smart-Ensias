package com.ensias.ensiasattendease.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ensias.ensiasattendease.models.CourseModel;
import com.ensias.ensiasattendease.services.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private final CourseService courseService ;

    @GetMapping()
    public ResponseEntity<List<CourseModel>> getAllCourses(@RequestParam(name = "page" , defaultValue = "0") int page , @RequestParam(name = "size" , defaultValue = "5") int size){
        return new ResponseEntity<>( courseService.getAllCourses(page, size), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createCourse(@RequestBody CourseModel course){
        if(course == null){
            return new ResponseEntity<>("{\"error\" : \"course is required\"}" , HttpStatus.BAD_REQUEST) ; 
        }
        else{
            CourseModel courseModel = courseService.saveCourseModel(course) ;
            if(courseModel == null){
                return new ResponseEntity<>("{\"error\" : \"course already exist or there an error\"}" , HttpStatus.BAD_REQUEST) ; 
            }
            return new ResponseEntity<>(courseService.saveCourseModel(course) , HttpStatus.CREATED) ;
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCourse(@RequestBody CourseModel course){
        if(course == null){
            return new ResponseEntity<>("{\"error\" : \"course is required\"}" , HttpStatus.BAD_REQUEST) ; 
        }
        else{
            CourseModel courseModel = courseService.updateCourse(course) ;
            if(courseModel == null){
                return new ResponseEntity<>("{\"error\" : \"course not found or there an error\"}" , HttpStatus.BAD_REQUEST) ; 
            }
            return new ResponseEntity<>(courseService.updateCourse(course) , HttpStatus.OK) ;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){
        if(id == null){
            return new ResponseEntity<>("{\"error\" : \"id is required\"}" , HttpStatus.BAD_REQUEST) ; 
        }
        else{
            CourseModel courseModel = courseService.getCourseById(id) ;
            if(courseModel == null){
                return new ResponseEntity<>("{\"error\" : \"course not found or there an error\"}" , HttpStatus.BAD_REQUEST) ; 
            }
            return new ResponseEntity<>(courseService.getCourseById(id) , HttpStatus.OK) ;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        if(id == null){
            return new ResponseEntity<>("{\"error\" : \"id is required\"}" , HttpStatus.BAD_REQUEST) ; 
        }
        else{
            Boolean returValue = courseService.deleteCourse(id) ;
            if(returValue == null){
                return new ResponseEntity<>("{\"error\" : \"course not found or there an error\"}" , HttpStatus.BAD_REQUEST) ; 
            }
            return new ResponseEntity<>("{\"deleted\" : true}" , HttpStatus.OK) ;
        }
    }


    
}
