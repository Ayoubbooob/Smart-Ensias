package com.ensias.ensiasattendease.services;

import com.ensias.ensiasattendease.models.EventModel;
import com.ensias.ensiasattendease.resources.RequestModels.AddEventRequest;

import java.util.List;

public interface EventService {

    public EventModel createEvent(AddEventRequest addEventRequest);

    public List<EventModel> getAllEvents();
}
