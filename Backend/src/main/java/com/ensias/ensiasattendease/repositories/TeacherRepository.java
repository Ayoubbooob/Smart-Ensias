package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.TeacherModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Long> {
}
