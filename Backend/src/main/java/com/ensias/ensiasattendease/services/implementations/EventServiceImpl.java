package com.ensias.ensiasattendease.services.implementations;

import com.ensias.ensiasattendease.models.EventModel;
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
    public EventModel createEvent(AddEventRequest addEventRequest){
        EventModel eventToSave = EventModel.builder()
                .start_date(LocalDate.parse(addEventRequest.getStart_date()))
                .end_date(LocalDate.parse(addEventRequest.getEnd_date()))
                .title(addEventRequest.getTitle())
                .build();

        eventRepository.save(eventToSave);
        return eventToSave;
    }

    public List<EventModel> getAllEvents() {
        return eventRepository.findAll();
    }
}
