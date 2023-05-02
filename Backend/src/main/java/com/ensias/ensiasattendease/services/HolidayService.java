package com.ensias.ensiasattendease.services;

import com.ensias.ensiasattendease.models.Holiday;
import com.ensias.ensiasattendease.resources.RequestModels.AddHolidayRequest;

public interface HolidayService {

    public Holiday createHoliday(AddHolidayRequest addHolidayRequest);
}
