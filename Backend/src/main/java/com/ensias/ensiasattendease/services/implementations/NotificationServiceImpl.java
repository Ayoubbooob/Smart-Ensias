package com.ensias.ensiasattendease.services.implementations;

import com.ensias.ensiasattendease.models.NotificationModel;
import com.ensias.ensiasattendease.models.NotificationStatus;
import com.ensias.ensiasattendease.models.StudentModel;
import com.ensias.ensiasattendease.repositories.NotificationRepository;
import com.ensias.ensiasattendease.repositories.StudentRepository;
import com.ensias.ensiasattendease.resources.responses.NotificationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@Transactional
@RequiredArgsConstructor
public class NotificationServiceImpl {

    final private NotificationRepository notificationRepository;
    final private StudentRepository studentRepository;

    public void addNotification(StudentModel student, String message) {
        NotificationModel notification = NotificationModel.builder()
                .message(message)
                .student(student)
                .createdAt(LocalDateTime.now())
                .notifStatus(NotificationStatus.UNREAD)
                .build();
        notificationRepository.save(notification);
    }

    public List<NotificationModel> getNotificationsForStudent(StudentModel student) {
        return notificationRepository.findByStudentOrderByCreatedAtDesc(student);
    }

    public List<NotificationResponse> getNotificationsByStudentId(Long studentId) {

            StudentModel student = studentRepository.findStudentById(studentId); // create a dummy Student object with the specified ID
            List<NotificationModel> notifications = notificationRepository.findByStudentOrderByCreatedAtDesc(student);

            List<NotificationResponse> responses = new ArrayList<>();
            for (NotificationModel notification : notifications) {
                NotificationResponse response = NotificationResponse.builder()
                        .id(notification.getId())
                        .message(notification.getMessage())
                        .createdAt(notification.getCreatedAt().toString())
                        .notifStatus(notification.getNotifStatus().toString())
                        .build();

                responses.add(response);
            }

            return responses;

    }

    public void markNotificationAsRead(Long notificationId) throws ChangeSetPersister.NotFoundException {
        Optional<NotificationModel> notificationOptional = notificationRepository.findById(notificationId);
        if (notificationOptional.isPresent()) {
            NotificationModel notification = notificationOptional.get();
            notification.setNotifStatus(NotificationStatus.READ);
            notificationRepository.save(notification);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }


}


