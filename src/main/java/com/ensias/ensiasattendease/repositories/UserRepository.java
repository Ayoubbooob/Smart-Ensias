package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);

    @Override
    List<User> findAll();

    @Override
    User save(User user);
}
