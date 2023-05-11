package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.HolidayModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends JpaRepository<HolidayModel, Long> {
}
