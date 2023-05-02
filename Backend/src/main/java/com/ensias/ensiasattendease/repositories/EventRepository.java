package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.Event;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
