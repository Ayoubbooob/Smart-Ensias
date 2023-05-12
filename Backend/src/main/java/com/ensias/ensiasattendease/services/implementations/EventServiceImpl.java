package com.ensias.ensiasattendease.services.implementations;

import com.ensias.ensiasattendease.models.EventModel;
import com.ensias.ensiasattendease.repositories.EventRepository;
import com.ensias.ensiasattendease.resources.RequestModels.AddEventRequest;
import com.ensias.ensiasattendease.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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



    public EventModel getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    public List<EventModel> getAllEvents() {
        return eventRepository.findAll();
    }


    public EventModel updateEvent(Long eventId, AddEventRequest addEventRequest) throws Exception {
        Optional<EventModel> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) {
            throw new Exception("Event not found with id: " + eventId);
        }
        EventModel existingEvent = optionalEvent.get();
        existingEvent.setStart_date(LocalDate.parse(addEventRequest.getStart_date()));
        existingEvent.setEnd_date(LocalDate.parse(addEventRequest.getEnd_date()));
        existingEvent.setTitle(addEventRequest.getTitle());
        eventRepository.save(existingEvent);
        return existingEvent;
    }

    public EventModel patchEvent(Long eventId, Map<String, Object> updates) throws Exception {
        Optional<EventModel> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) {
            throw new Exception("Event not found with id: " + eventId);
        }
        EventModel existingEvent = optionalEvent.get();

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            switch (field) {
                case "start_date" -> existingEvent.setStart_date(LocalDate.parse(value.toString()));
                case "end_date" -> existingEvent.setEnd_date(LocalDate.parse(value.toString()));
                case "title" -> existingEvent.setTitle(value.toString());
                default -> throw new IllegalArgumentException("Invalid field: " + field);
            }
        }

        eventRepository.save(existingEvent);
        return existingEvent;
    }


    public void deleteEvent(Long eventId) throws Exception {
        Optional<EventModel> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            eventRepository.delete(event.get());
        } else {
            throw new Exception("Event with ID " + eventId + " not found");
        }
    }
}
