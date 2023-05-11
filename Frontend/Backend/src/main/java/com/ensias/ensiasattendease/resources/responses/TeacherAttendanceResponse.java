package com.ensias.ensiasattendease.resources.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherAttendanceResponse {

    private Long id;

    private String matricule;
    private String firstname;

    private String lastname;

//    private String subject; // course

    private String start_time; //started

    private String end_time; //ended

    private String date; //takedTime;

    private String status;
}
