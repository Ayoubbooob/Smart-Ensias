package com.ensias.ensiasattendease.services;

import java.util.List;
import java.util.Optional;

import com.ensias.ensiasattendease.models.UserModel;

public interface UserService {
    List<UserModel> getAllUser();
    UserModel saveUserModel(UserModel user);
    Optional<UserModel> getUserById(Long id) ; 
    Boolean deleteUser(String email);

    //ADD method that you will implement later - Functionalities
    // Ex : getUsers -- to get all users
    // Ex : addUser -- to create new user ..
}
