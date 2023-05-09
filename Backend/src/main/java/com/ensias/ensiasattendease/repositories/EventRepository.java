package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.EventModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface EventRepository extends JpaRepository<EventModel, Long> {
}
