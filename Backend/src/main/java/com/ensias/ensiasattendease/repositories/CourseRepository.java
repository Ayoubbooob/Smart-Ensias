package com.ensias.ensiasattendease.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensias.ensiasattendease.models.CourseModel;

public interface CourseRepository extends JpaRepository<CourseModel, Long> {
    List<CourseModel> findAll();
    CourseModel save(CourseModel course);
    Optional<CourseModel> findById(Long id);
    void deleteById(Long id);
}
