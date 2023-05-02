package com.ensias.ensiasattendease.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public  abstract class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column(unique = true)
//    @NotEmpty(message = "le nom d'utilisateur est requis ")
//    private String username;

    //Note : J'ai supprime username, car on n'a pas besoin
    @Column(unique = true)
    private String phone  ;

    private LocalDate date_of_birth;

    private String image_url ;

    @Email(message = "Email Invalide")
    private    String email ;

    @Enumerated(EnumType.STRING)
    private GenreUser genre = GenreUser.HOMME;
    @NotEmpty(message = "Le Mot de passe est requis")
//    @Min(6)
    private  String password;

    @NotEmpty(message = "le prenom est requis")
    private String first_name;

    @NotEmpty(message = "le nom est requis")
    private String last_name;


    private String role;

//    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Token> tokens ;


    //TODO - Fix Roles with list of permissions
//    @Enumerated(EnumType.STRING)
//    private Role role;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public String getUsername() {
        return email;
    }




    @Override
    public boolean isAccountNonExpired() {
        return true;
    }



    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String getPassword() {
        return password;
    }




}
