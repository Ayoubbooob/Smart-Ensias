package com.ensias.ensiasattendease.controllers;


import com.ensias.ensiasattendease.models.Event;
import com.ensias.ensiasattendease.resources.RequestModels.AddEventRequest;
import com.ensias.ensiasattendease.services.implementations.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/event")
@RequiredArgsConstructor

public class EventController {

    private final EventServiceImpl eventService;
    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody AddEventRequest addEventRequest){
        return ResponseEntity.ok(eventService.createEvent(addEventRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }



}