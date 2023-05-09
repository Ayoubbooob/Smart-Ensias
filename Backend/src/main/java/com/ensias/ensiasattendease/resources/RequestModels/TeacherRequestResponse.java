package com.ensias.ensiasattendease.resources.RequestModels;


import com.ensias.ensiasattendease.models.Role;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequestResponse {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;

//    private String image_url;

    private String gender;

    private String date_of_birth;

    private String matricule;
    private String joining_date;
}
