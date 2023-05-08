package com.ensias.ensiasattendease.services;

import com.ensias.ensiasattendease.models.EventModel;
import com.ensias.ensiasattendease.resources.RequestModels.AddEventRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EventService {

     EventModel createEvent(AddEventRequest addEventRequest);

     EventModel getEventById(Long eventId);
     List<EventModel> getAllEvents();

      EventModel updateEvent(Long eventId, AddEventRequest addEventRequest) throws Exception;

     EventModel patchEvent(Long eventId, Map<String, Object> updates) throws Exception;

    public void deleteEvent(Long eventId) throws Exception;
}
