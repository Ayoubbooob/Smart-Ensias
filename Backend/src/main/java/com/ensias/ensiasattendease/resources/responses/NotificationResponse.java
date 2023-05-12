package com.ensias.ensiasattendease.resources.responses;


import com.ensias.ensiasattendease.models.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    private Long id;
    private String message;
    private String createdAt;

    private String notifStatus;
}
