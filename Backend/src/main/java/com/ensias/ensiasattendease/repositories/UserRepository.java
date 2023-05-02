package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);


    List<User> findAll();

    User save(User user);

    void deleteByEmail(String email);

    Optional<User> findById(Long id) ;

    Optional<User> findByEmail(String email);

}
