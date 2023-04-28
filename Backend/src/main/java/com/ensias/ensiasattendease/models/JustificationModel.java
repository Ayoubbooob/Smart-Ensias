package com.ensias.ensiasattendease.models;


import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Justification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JustificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ; 
    @Lob
    @NotBlank(message = "spt Ajoute une justification")
    private String justification ; 
    @NotBlank(message = "Spt Attache un fichier")
    private String attache ; 
    @NotNull(message = "stp Choisi un etat")
    @Enumerated(EnumType.STRING)
    private JustificationEtat etat = JustificationEtat.EN_ATTENTE ; 
    @NotNull(message ="la justification doit être lier à une abscense")
    @OneToMany(mappedBy = "justification" , fetch = FetchType.LAZY)
    private Collection<AttendanceModel> attendance ; 
    
}
