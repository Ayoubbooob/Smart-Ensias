package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findUserById(Long id);

    @Override
    List<UserModel> findAll();

    @Override
    UserModel save(UserModel user);
}
