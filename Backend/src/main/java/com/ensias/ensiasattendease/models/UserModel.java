package com.ensias.ensiasattendease.models;


import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(unique = true)
    @NotEmpty(message = "le nom d'utilisateur est requis ")
    protected String username;

    @Column(unique = true)
    protected String phone  ; 
    // @NotEmpty(message = "la date de naissance est requise")
    private LocalDate date_of_birth;


    @Email(message = "Email Invalide")
    protected  String email ;

    @Enumerated(EnumType.STRING)
    private GenreUser genre = GenreUser.HOMME;

    private String image_url ; 


    @NotEmpty(message = "Le Mot de passe est requis")
    protected String password;

    @NotEmpty(message = "le prenom est requis")
    protected String first_name;

    @NotEmpty(message = "le nom est requis")
    protected String last_name;
}
