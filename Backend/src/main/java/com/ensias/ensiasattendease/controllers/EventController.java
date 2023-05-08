package com.ensias.ensiasattendease.controllers;


import com.ensias.ensiasattendease.models.EventModel;
import com.ensias.ensiasattendease.resources.RequestModels.AddEventRequest;
import com.ensias.ensiasattendease.services.implementations.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("api/v1/event")
//@RequiredArgsConstructor
//public class EventController {
//
//    private final EventServiceImpl eventService;
//    @PostMapping("/create")
//    public ResponseEntity<EventModel> createEvent(@RequestBody AddEventRequest addEventRequest){
//        return ResponseEntity.ok(eventService.createEvent(addEventRequest));
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<EventModel>> getAllEvents(){
//        return ResponseEntity.ok(eventService.getAllEvents());
//    }
//
//
//
//}


@RestController
@RequestMapping("api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventServiceImpl eventService;

    @PostMapping("/create")
    public ResponseEntity<EventModel> createEvent(@RequestBody AddEventRequest addEventRequest) throws Exception {
        try {
            EventModel event = eventService.createEvent(addEventRequest);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            throw new Exception("Failed to create event: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventModel>> getAllEvents() throws Exception {
        try {
            List<EventModel> events = eventService.getAllEvents();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            throw new Exception("Failed to get events: " + e.getMessage());
        }
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventModel> getEventById(@PathVariable Long eventId) throws Exception {
        try {
            EventModel event = eventService.getEventById(eventId);
            if(event == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            throw new Exception("Failed to get event: " + e.getMessage());
        }
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventModel> updateEvent(@PathVariable Long eventId, @RequestBody AddEventRequest addEventRequest) throws Exception {
        try {
            EventModel updatedEvent = eventService.updateEvent(eventId, addEventRequest);
            return ResponseEntity.ok(updatedEvent);
        } catch (Exception e) {
            throw new Exception("Failed to update event: " + e.getMessage());
        }
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<EventModel> patchEvent(@PathVariable Long eventId, @RequestBody Map<String, Object> updates) throws Exception {
        try {
            EventModel patchedEvent = eventService.patchEvent(eventId, updates);
            return ResponseEntity.ok(patchedEvent);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) throws Exception {
        try {
            eventService.deleteEvent(eventId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}

