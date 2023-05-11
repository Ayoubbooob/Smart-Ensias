package com.ensias.ensiasattendease.services;


import com.ensias.ensiasattendease.models.NotificationModel;
import com.ensias.ensiasattendease.models.StudentModel;
import com.ensias.ensiasattendease.resources.responses.NotificationResponse;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface NotificationService {

    void addNotification(StudentModel student, String message);

    List<NotificationResponse> getNotificationsByStudentId(Long studentId);


    void markNotificationAsRead(Long notificationId) throws ChangeSetPersister.NotFoundException;

}