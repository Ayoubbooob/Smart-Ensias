package com.ensias.ensiasattendease.controllers;

import com.ensias.ensiasattendease.models.HolidayModel;
import com.ensias.ensiasattendease.models.HolidayModel;
import com.ensias.ensiasattendease.resources.RequestModels.AddHolidayRequest;
import com.ensias.ensiasattendease.resources.RequestModels.AddHolidayRequest;
import com.ensias.ensiasattendease.services.implementations.HolidayServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/holiday")
public class HolidayController {

    private final HolidayServiceImpl holidayService;


    @PostMapping("/create")
    public ResponseEntity<HolidayModel> createHoliday(@RequestBody AddHolidayRequest addHolidayRequest) throws Exception {
        try {
            HolidayModel holiday = holidayService.createHoliday(addHolidayRequest);
            return ResponseEntity.ok(holiday);
        } catch (Exception e) {
            throw new Exception("Failed to update holiday: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<HolidayModel>> getAllHolidays() throws Exception{
        try{
            List<HolidayModel> holidays =   holidayService.getAllHolidays();
            return ResponseEntity.ok(holidays);
        }catch(Exception e){
            throw new Exception("Failed to get holidays: " + e.getMessage());
        }
    }

    @GetMapping("/{holidayId}")
    public ResponseEntity<HolidayModel> getHolidayById(@PathVariable Long holidayId) throws Exception {
        try {
            HolidayModel holiday = holidayService.getHolidayById(holidayId);
            if(holiday == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(holiday);
        } catch (Exception e) {
            throw new Exception("Failed to get holiday: " + e.getMessage());
        }
    }

    @PutMapping("/{holidayId}")
    public ResponseEntity<HolidayModel> updateHoliday(@PathVariable Long holidayId, @RequestBody AddHolidayRequest AddHolidayRequest) throws Exception {
        try {
            HolidayModel updatedHoliday = holidayService.updateHoliday(holidayId, AddHolidayRequest);
            return ResponseEntity.ok(updatedHoliday);
        } catch (Exception e) {
            throw new Exception("Failed to update Holiday: " + e.getMessage());
        }
    }

    @PatchMapping("/{holidayId}")
    public ResponseEntity<HolidayModel> patchHoliday(@PathVariable Long holidayId, @RequestBody Map<String, Object> updates) throws Exception {
        try {
            HolidayModel patchedHoliday = holidayService.patchHoliday(holidayId, updates);
            return ResponseEntity.ok(patchedHoliday);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();        }
    }

    @DeleteMapping("/{holidayId}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long holidayId) throws Exception {
        try {
            holidayService.deleteHoliday(holidayId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
