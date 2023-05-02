package com.ensias.ensiasattendease.services.implementations;

import com.ensias.ensiasattendease.models.Event;
import com.ensias.ensiasattendease.repositories.EventRepository;
import com.ensias.ensiasattendease.resources.RequestModels.AddEventRequest;
import com.ensias.ensiasattendease.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;
    public Event createEvent(AddEventRequest addEventRequest){
        Event eventToSave = Event.builder()
                .start_date(LocalDate.parse(addEventRequest.getStart_date()))
                .end_date(LocalDate.parse(addEventRequest.getEnd_date()))
                .build();

        eventRepository.save(eventToSave);
        return eventToSave;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
