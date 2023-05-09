package com.ensias.ensiasattendease.services.implementations;


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
}
