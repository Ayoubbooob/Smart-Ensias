package com.ensias.ensiasattendease.controllers;


<<<<<<< HEAD
import com.ensias.ensiasattendease.models.UserModel;
import com.ensias.ensiasattendease.services.UserService;
=======
import com.ensias.ensiasattendease.models.User;
import com.ensias.ensiasattendease.repositories.TeacherRepository;
>>>>>>> 84e99edd3795a07dad7eda8513d2f6228c358c7e
import com.ensias.ensiasattendease.services.implementations.UserServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

<<<<<<< HEAD
    @Autowired
    private final UserService userService;
=======
    private final UserServiceImpl userService;
    private final TeacherRepository teacherRepository;

    private final PasswordEncoder passwordEncoder;

>>>>>>> 84e99edd3795a07dad7eda8513d2f6228c358c7e

    //This method returns list of users

    
    //TODO- ADD Response Class, that return elegant json file as Response to the postman user
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<?> CreateUser(@RequestBody UserModel user){
        if(user == null){
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST) ; 
        }
        else{
            return new ResponseEntity<>(userService.saveUserModel(user) , HttpStatus.CREATED) ;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUSerById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id) , HttpStatus.OK) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email){
        if(email == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ; 
        }
        else{
            return new ResponseEntity<>(userService.deleteUser(email) , HttpStatus.ACCEPTED);
        }
    }





}
