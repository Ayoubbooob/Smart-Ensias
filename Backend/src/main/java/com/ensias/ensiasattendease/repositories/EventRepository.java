package com.ensias.ensiasattendease.repositories;

import com.ensias.ensiasattendease.models.EventModel;
import com.ensias.ensiasattendease.resources.RequestModels.AddEventRequest;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<EventModel, Long> {

}
