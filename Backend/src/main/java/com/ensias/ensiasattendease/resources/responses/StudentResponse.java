package com.ensias.ensiasattendease.resources.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private String firstname;
    private String lastname;

    private String phone;

    private String image_url;

    private String email;

    private String date_of_birth;

    private String gender;

    private String cne;

}
