package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findUserById(Long id);

    @Override
    List<UserModel> findAll();

    @Override
    UserModel save(UserModel user);

    @Override
    void delete(UserModel user);

    @Override
    Optional<UserModel> findById(Long id) ;


}
