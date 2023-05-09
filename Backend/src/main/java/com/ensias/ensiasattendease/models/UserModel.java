package com.ensias.ensiasattendease.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
   
    @Column(unique = true)
    protected String phone  ; 
    // @NotEmpty(message = "la date de naissance est requise")
    private LocalDate date_of_birth;


    @Email(message = "Email Invalide")
    @Column(unique = true)
    protected  String email ;

    @Enumerated(EnumType.STRING)
    private GenreUser genre = GenreUser.MALE;

    private String image_url ; 


    @NotEmpty(message = "Le Mot de passe est requis")
    protected String password;

    @NotEmpty(message = "le prenom est requis")
    protected String first_name;

    @NotEmpty(message = "le nom est requis")
    protected String last_name;

    //ADDED THIS
    @Enumerated(EnumType.STRING)
    private Role role;

//    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TokenModel> tokens ;


    //TODO - Fix Roles with list of permissions
//    @Enumerated(EnumType.STRING)
//    private Role role;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities(); //ADDED THIS
//        return null;
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
