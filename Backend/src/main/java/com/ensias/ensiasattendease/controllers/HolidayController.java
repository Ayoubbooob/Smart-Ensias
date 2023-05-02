package com.ensias.ensiasattendease.controllers;

import com.ensias.ensiasattendease.models.Holiday;
import com.ensias.ensiasattendease.resources.RequestModels.AddHolidayRequest;
import com.ensias.ensiasattendease.services.implementations.HolidayServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/holiday")
public class HolidayController {

    private final HolidayServiceImpl holidayService;

    @PostMapping("/create")
    public ResponseEntity<Holiday> createHoliday(@RequestBody AddHolidayRequest addHolidayRequest){
        return ResponseEntity.ok(holidayService.createHoliday(addHolidayRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Holiday>> getAllHolidays(){
        return ResponseEntity.ok(holidayService.getAllHolidays());
    }
}
