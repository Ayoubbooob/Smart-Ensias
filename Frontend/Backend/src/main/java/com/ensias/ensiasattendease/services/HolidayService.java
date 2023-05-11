package com.ensias.ensiasattendease.services;

import com.ensias.ensiasattendease.models.HolidayModel;
import com.ensias.ensiasattendease.resources.RequestModels.AddHolidayRequest;

import java.util.List;
import java.util.Map;

public interface HolidayService {

     HolidayModel createHoliday(AddHolidayRequest addHolidayRequest);

     HolidayModel getHolidayById(Long holidayId);

    List<HolidayModel> getAllHolidays();

    HolidayModel patchHoliday(Long holidayId, Map<String, Object> updates) throws Exception;

    HolidayModel updateHoliday(Long holidayId, AddHolidayRequest addHolidayRequest) throws Exception;

    public void deleteHoliday(Long holidayId) throws Exception;


}
