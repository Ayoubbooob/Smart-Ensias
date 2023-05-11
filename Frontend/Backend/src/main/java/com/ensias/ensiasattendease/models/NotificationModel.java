package com.ensias.ensiasattendease.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NotificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentModel student;

    @Column(nullable = false)
    private LocalDateTime createdAt;


    @Enumerated(EnumType.STRING)

    private NotificationStatus notifStatus;

    // constructors, getters and setters
}
