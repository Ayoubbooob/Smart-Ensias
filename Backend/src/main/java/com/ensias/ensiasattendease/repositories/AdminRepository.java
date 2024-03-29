package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long> {

}
