package com.ensias.ensiasattendease.controllers;


import com.ensias.ensiasattendease.models.UserModel;
import com.ensias.ensiasattendease.repositories.UserRepository;
import com.ensias.ensiasattendease.services.UserService.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    //This method returns list of users

    
    //TODO- ADD Response Class, that return elegant json file as Response to the postman user
    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers(){
        List<UserModel> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<UserModel> CreateUser(@RequestBody UserModel user){
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
}
