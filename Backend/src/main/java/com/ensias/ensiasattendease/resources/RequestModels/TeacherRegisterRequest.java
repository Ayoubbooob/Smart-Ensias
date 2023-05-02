package com.ensias.ensiasattendease.resources.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRegisterRequest extends RegisterRequest{
    private String matricule;
}


