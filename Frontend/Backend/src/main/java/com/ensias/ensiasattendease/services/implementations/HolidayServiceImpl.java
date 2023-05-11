package com.ensias.ensiasattendease.services.implementations;


import com.ensias.ensiasattendease.models.EventModel;
import com.ensias.ensiasattendease.models.HolidayModel;
import com.ensias.ensiasattendease.models.HolidayModel;
import com.ensias.ensiasattendease.models.HolidayModel;
import com.ensias.ensiasattendease.repositories.HolidayRepository;
import com.ensias.ensiasattendease.resources.RequestModels.AddHolidayRequest;
import com.ensias.ensiasattendease.services.HolidayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;

    public HolidayModel createHoliday(AddHolidayRequest addHolidayRequest){

            HolidayModel holidayModelToSave = HolidayModel.builder()
                    .title(addHolidayRequest.getTitle())
                    .type(addHolidayRequest.getType())
                    .start_date(LocalDate.parse(addHolidayRequest.getStart_date()))
                    .end_date(LocalDate.parse(addHolidayRequest.getEnd_date()))
                    .build();
            holidayRepository.save(holidayModelToSave);
            return holidayModelToSave;

    }

    public List<HolidayModel> getAllHolidays() {
        return holidayRepository.findAll();
    }

    public HolidayModel getHolidayById(Long holidayId) {
        return holidayRepository.findById(holidayId).orElse(null);
    }

    public HolidayModel updateHoliday(Long holidayId, AddHolidayRequest addHolidayRequest) throws Exception {
        Optional<HolidayModel> optionalHoliday = holidayRepository.findById(holidayId);
        if (optionalHoliday.isEmpty()) {
            throw new Exception("Holiday not found with id: " + holidayId);
        }
        HolidayModel existingHoliday = optionalHoliday.get();
        existingHoliday.setStart_date(LocalDate.parse(addHolidayRequest.getStart_date()));
        existingHoliday.setEnd_date(LocalDate.parse(addHolidayRequest.getEnd_date()));
        existingHoliday.setTitle(addHolidayRequest.getTitle());
        existingHoliday.setType(addHolidayRequest.getType());
        holidayRepository.save(existingHoliday);
        return existingHoliday;
    }
    
    public HolidayModel patchHoliday(Long holidayId, Map<String, Object> updates) throws Exception {
        Optional<HolidayModel> optionalHoliday = holidayRepository.findById(holidayId);
        if (optionalHoliday.isEmpty()) {
            throw new Exception("Holiday not found with id: " + holidayId);
        }
        HolidayModel existingHoliday = optionalHoliday.get();

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            switch (field) {
                case "start_date" -> existingHoliday.setStart_date(LocalDate.parse(value.toString()));
                case "end_date" -> existingHoliday.setEnd_date(LocalDate.parse(value.toString()));
                case "title" -> existingHoliday.setTitle(value.toString());
                case "type" -> existingHoliday.setType(value.toString());

                default -> throw new IllegalArgumentException("Invalid field: " + field);
            }
        }

        holidayRepository.save(existingHoliday);
        return existingHoliday;
    }
//    public HolidayModel updateHoliday(Long holidayId, AddHolidayRequest addHolidayRequest) {
//    }


    public void deleteHoliday(Long holidayId) throws Exception {
        Optional<HolidayModel> holiday = holidayRepository.findById(holidayId);
        if (holiday.isPresent()) {
            holidayRepository.delete(holiday.get());
        } else {
            throw new Exception("Holiday with ID " + holidayId + " not found");
        }
    }
}
