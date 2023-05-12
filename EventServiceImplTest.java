package com.ensias.ensiasattendease.services.implementations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ensias.ensiasattendease.models.EventModel;
import com.ensias.ensiasattendease.repositories.EventRepository;
import com.ensias.ensiasattendease.resources.RequestModels.AddEventRequest;
import com.ensias.ensiasattendease.services.implementations.EventServiceImpl;

public class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testGetAllEvents() {
        EventModel event1 = new EventModel();
        event1.setId(1L);
        event1.setStart_date(LocalDate.of(2023, 5, 15));
        event1.setEnd_date(LocalDate.of(2023, 5, 17));
        event1.setTitle("Test Event 1");

        EventModel event2 = new EventModel();
        event2.setId(2L);
        event2.setStart_date(LocalDate.of(2023, 5, 20));
        event2.setEnd_date(LocalDate.of(2023, 5, 22));
        event2.setTitle("Test Event 2");

        when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2));

        List<EventModel> events = eventService.getAllEvents();

        Assertions.assertNotNull(events);
        Assertions.assertEquals(2, events.size());

        EventModel result1 = events.get(0);
        Assertions.assertEquals(event1.getId(), result1.getId());
        Assertions.assertEquals(event1.getStart_date(), result1.getStart_date());
        Assertions.assertEquals(event1.getEnd_date(), result1.getEnd_date());
        Assertions.assertEquals(event1.getTitle(), result1.getTitle());

        EventModel result2 = events.get(1);
        Assertions.assertEquals(event2.getId(), result2.getId());
        Assertions.assertEquals(event2.getStart_date(), result2.getStart_date());
        Assertions.assertEquals(event2.getEnd_date(), result2.getEnd_date());
        Assertions.assertEquals(event2.getTitle(), result2.getTitle());
    }




}
