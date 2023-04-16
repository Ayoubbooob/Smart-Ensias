package com.ensias.ensiasattendease.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User getUser(Long id);

    //ADD method that you will implement later - Functionalities
    // Ex : getUsers -- to get all users
    // Ex : addUser -- to create new user ..

    @Service
    @RequiredArgsConstructor
    @Transactional
    @Slf4j
    class UserServiceImpl implements UserService{
        @Override
        public User getUser(Long id) {
            return null;
        }

        // implement here all methods that you've added in UserService
    }
}
