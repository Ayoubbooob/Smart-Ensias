package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findUserById(Long id);

    List<UserModel> findAll();

    UserModel save(UserModel user);

    void deleteByEmail(String email);

    Optional<UserModel> findById(Long id) ;


}
