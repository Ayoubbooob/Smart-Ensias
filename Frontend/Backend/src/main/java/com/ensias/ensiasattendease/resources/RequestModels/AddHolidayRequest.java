package com.ensias.ensiasattendease.resources.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddHolidayRequest {

    private String title;
    private String type;
    private String start_date;
    private String end_date;
}
