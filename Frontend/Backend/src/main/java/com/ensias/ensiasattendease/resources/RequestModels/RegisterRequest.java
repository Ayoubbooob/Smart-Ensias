package com.ensias.ensiasattendease.resources.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private String phone;
//    private String role;

    private String image_url;

    private String gender;

    private String date_of_birth;



}




