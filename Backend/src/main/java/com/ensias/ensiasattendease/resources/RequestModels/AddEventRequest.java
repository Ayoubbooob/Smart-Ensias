package com.ensias.ensiasattendease.resources.RequestModels;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEventRequest {

    private String start_date;
    private String end_date;
}
