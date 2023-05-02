package com.ensias.ensiasattendease.services;

import java.util.List;
import java.util.Optional;

import com.ensias.ensiasattendease.models.User;

public interface UserService {
    List<User> getAllUser();
    User saveUserModel(User user);
    Optional<User> getUserById(Long id) ;
    Boolean deleteUser(String email);

    //ADD method that you will implement later - Functionalities
    // Ex : getUsers -- to get all users
    // Ex : addUser -- to create new user ..
}


