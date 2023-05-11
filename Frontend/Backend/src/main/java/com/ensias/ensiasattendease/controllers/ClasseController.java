package com.ensias.ensiasattendease.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensias.ensiasattendease.models.ClasseModel;
import com.ensias.ensiasattendease.services.ClasseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classe")
public class ClasseController {

    private final ClasseService classeService;

    @GetMapping()
    public ResponseEntity<?> getAllClasse(){
        List<ClasseModel> classeModel = classeService.getAllClasses() ; 
        if(classeModel == null){
            return new ResponseEntity<>("{\"error\" : \"classe not found\"}" , HttpStatus.BAD_REQUEST) ; 
        }
        return new ResponseEntity<>(classeModel, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ClasseModel> createClasse(@RequestBody ClasseModel classe){
        return new ResponseEntity<>(classeService.saveClasse(classe), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<?> updateClasse(@RequestBody ClasseModel classe){
        ClasseModel classeModel = classeService.getClasseById(classe.getId()) ;
        if(classeModel == null){
            return new ResponseEntity<>("{\"error\" : \"classe not found or there is an error\"}" , HttpStatus.BAD_REQUEST) ; 
        }
        return new ResponseEntity<>(classeModel, HttpStatus.OK);
    }
    
    @DeleteMapping("/{classeId}")
    public ResponseEntity<?> deleteClasse(@PathVariable Long classeId){
        Boolean classeModel = classeService.deleteClasse(classeId) ;
        if(classeModel == false){
            return new ResponseEntity<>("{\"deleted\" : false}" , HttpStatus.BAD_REQUEST) ; 
        }
        return new ResponseEntity<>("{\"deleted\" : true}", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getClasseById(@PathVariable Long id){
        ClasseModel classeModel = classeService.getClasseById(id) ;
        if(classeModel == null){
            return new ResponseEntity<>("{\"error\" : \"classe not found\"}" , HttpStatus.BAD_REQUEST) ; 
        }
        return new ResponseEntity<>(classeModel, HttpStatus.OK);
    }
    
}
