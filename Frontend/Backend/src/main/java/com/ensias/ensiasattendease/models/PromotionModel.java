// package com.ensias.ensiasattendease.models;

// import java.util.Collection;

// import org.hibernate.annotations.ManyToAny;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.OneToMany;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// // @Entity
// // @Data
// // @AllArgsConstructor
// // @NoArgsConstructor
// public class PromotionModel {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id ;
//     private String name ; 
//     private Data StartDate ;
//     private Data EndDate ; 
//     @ManyToMany(mappedBy = "promotion" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
//     @JsonIgnoreProperties("promotion")
//     private Collection<FiliereModel> filiere ;
// }
