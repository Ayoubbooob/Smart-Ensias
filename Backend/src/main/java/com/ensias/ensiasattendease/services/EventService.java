package com.ensias.ensiasattendease.services;

import com.ensias.ensiasattendease.models.EventModel;
import com.ensias.ensiasattendease.resources.RequestModels.AddEventRequest;

import java.util.List;

public interface EventService {

     EventModel createEvent(AddEventRequest addEventRequest);

     List<EventModel> getAllEvents();
}
