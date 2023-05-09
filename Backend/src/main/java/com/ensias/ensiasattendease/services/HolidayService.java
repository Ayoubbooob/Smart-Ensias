package com.ensias.ensiasattendease.services;

import com.ensias.ensiasattendease.models.HolidayModel;
import com.ensias.ensiasattendease.resources.RequestModels.AddHolidayRequest;

public interface HolidayService {

    public HolidayModel createHoliday(AddHolidayRequest addHolidayRequest);
}
