package com.ensias.ensiasattendease.controllers;


import com.ensias.ensiasattendease.models.NotificationModel;
import com.ensias.ensiasattendease.resources.responses.NotificationResponse;
import com.ensias.ensiasattendease.services.implementations.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationServiceImpl notificationService;
    @GetMapping("/{studentId}")
    public ResponseEntity<List<NotificationResponse>> getNotificationsByStudentId(@PathVariable Long studentId) {
        List<NotificationResponse> notifications = notificationService.getNotificationsByStudentId(studentId);
        return ResponseEntity.ok(notifications);
    }


    @PutMapping("/{notificationId}")
    public ResponseEntity<?> markNotificationAsRead(@PathVariable Long notificationId) {
        try {
            notificationService.markNotificationAsRead(notificationId);
            return ResponseEntity.ok().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
