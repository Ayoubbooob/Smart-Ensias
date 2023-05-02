package com.ensias.ensiasattendease.services.implementations;


import com.ensias.ensiasattendease.models.Holiday;
import com.ensias.ensiasattendease.repositories.HolidayRepository;
import com.ensias.ensiasattendease.resources.RequestModels.AddHolidayRequest;
import com.ensias.ensiasattendease.services.HolidayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;

    public Holiday createHoliday(AddHolidayRequest addHolidayRequest){

            Holiday holidayToSave = Holiday.builder()
                    .title(addHolidayRequest.getTitle())
                    .type(addHolidayRequest.getType())
                    .start_date(LocalDate.parse(addHolidayRequest.getStart_date()))
                    .end_date(LocalDate.parse(addHolidayRequest.getEnd_date()))
                    .build();
            holidayRepository.save(holidayToSave);
            return holidayToSave;

    }

    public List<Holiday> getAllHolidays() {
        return holidayRepository.findAll();
    }
}
