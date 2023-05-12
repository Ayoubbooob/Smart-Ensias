package com.ensias.ensiasattendease.services;


import com.ensias.ensiasattendease.models.HolidayModel;
import com.ensias.ensiasattendease.repositories.HolidayRepository;
import com.ensias.ensiasattendease.resources.RequestModels.AddHolidayRequest;
import com.ensias.ensiasattendease.services.implementations.HolidayServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HolidayServiceImplTest {

    @InjectMocks
    private HolidayServiceImpl holidayService;

    @Mock
    private HolidayRepository holidayRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createHolidayTest() {
        // given
        AddHolidayRequest addHolidayRequest = new AddHolidayRequest();
        addHolidayRequest.setTitle("Christmas");
        addHolidayRequest.setType("National");
        addHolidayRequest.setStart_date("2023-12-25");
        addHolidayRequest.setEnd_date("2023-12-26");
        HolidayModel holidayModelToSave = new HolidayModel();
        holidayModelToSave.setTitle("Christmas");
        holidayModelToSave.setType("National");
        holidayModelToSave.setStart_date(LocalDate.parse("2023-12-25"));
        holidayModelToSave.setEnd_date(LocalDate.parse("2023-12-26"));

        when(holidayRepository.save(any(HolidayModel.class))).thenReturn(holidayModelToSave);

        // when
        HolidayModel createdHoliday = holidayService.createHoliday(addHolidayRequest);

        // then
        assertNotNull(createdHoliday);
        assertEquals("Christmas", createdHoliday.getTitle());
        assertEquals("National", createdHoliday.getType());
        assertEquals(LocalDate.parse("2023-12-25"), createdHoliday.getStart_date());
        assertEquals(LocalDate.parse("2023-12-26"), createdHoliday.getEnd_date());

        verify(holidayRepository, times(1)).save(any(HolidayModel.class));

            //Comment here to test again

    }

    @Test
    public void getAllHolidaysTest() {
        // given
        HolidayModel holiday1 = new HolidayModel();
        holiday1.setTitle("Holiday 1");
        holiday1.setType("Type 1");
        holiday1.setStart_date(LocalDate.parse("2023-01-01"));
        holiday1.setEnd_date(LocalDate.parse("2023-01-02"));

        HolidayModel holiday2 = new HolidayModel();
        holiday2.setTitle("Holiday 2");
        holiday2.setType("Type 2");
        holiday2.setStart_date(LocalDate.parse("2023-01-03"));
        holiday2.setEnd_date(LocalDate.parse("2023-01-04"));

        List<HolidayModel> expectedHolidays = Arrays.asList(holiday1, holiday2);

        when(holidayRepository.findAll()).thenReturn(expectedHolidays);

        // when
        List<HolidayModel> holidays = holidayService.getAllHolidays();

        // then
        assertNotNull(holidays);
        assertEquals(2, holidays.size());
        assertEquals(expectedHolidays, holidays);

        verify(holidayRepository, times(1)).findAll();
    }

    // add more tests for other methods in the service class as needed

}
