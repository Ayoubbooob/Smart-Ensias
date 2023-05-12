package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.NotificationModel;
import com.ensias.ensiasattendease.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {

    List<NotificationModel> findByStudentOrderByCreatedAtDesc(StudentModel student);
}
