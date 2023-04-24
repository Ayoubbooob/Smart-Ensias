package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findUserById(Long id);

    List<UserModel> findAll();

    UserModel save(UserModel user);

    void delete(UserModel user);

    Optional<UserModel> findById(Long id) ;


}
