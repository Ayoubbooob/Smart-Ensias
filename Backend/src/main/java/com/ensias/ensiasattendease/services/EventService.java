package com.ensias.ensiasattendease.services;

import com.ensias.ensiasattendease.models.Event;
import com.ensias.ensiasattendease.resources.RequestModels.AddEventRequest;

import java.util.List;

public interface EventService {

    public Event createEvent(AddEventRequest addEventRequest);

    public List<Event> getAllEvents();
}
