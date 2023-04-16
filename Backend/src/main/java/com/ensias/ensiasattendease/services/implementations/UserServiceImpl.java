package com.ensias.ensiasattendease.services.implementations;

import com.ensias.ensiasattendease.repositories.UserRepository;
import com.ensias.ensiasattendease.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public User getUser(Long id) {
        return null;
    }

    // implement here all methods that you've already put it in UserService
}
