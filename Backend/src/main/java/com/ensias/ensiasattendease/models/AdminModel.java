package com.ensias.ensiasattendease.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Admin")
@NoArgsConstructor
@AllArgsConstructor
public class AdminModel extends UserModel {


    private String matricule;


//    public static AdminBuilder builder() {
//        return new AdminBuilder();
//    }

}
