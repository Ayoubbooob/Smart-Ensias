package com.ensias.ensiasattendease.services.implementations;

import com.ensias.ensiasattendease.models.UserModel;
import com.ensias.ensiasattendease.repositories.UserRepository;
import com.ensias.ensiasattendease.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Autowired
    UserServiceImpl(UserRepository userRepository){
        log.info("Starting userService");
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> getAllUser() {
        return  userRepository.findAll();
    }

    @Override
    public UserModel saveUserModel(UserModel user){
        return userRepository.save(user) ; 
    }

    // implement here all methods that you've already put it in UserService
}
