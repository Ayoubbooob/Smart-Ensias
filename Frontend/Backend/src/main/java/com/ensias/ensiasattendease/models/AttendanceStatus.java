package com.ensias.ensiasattendease.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AttendanceStatus {
    @JsonProperty("PRESENT")
    PRESENT, 
    @JsonProperty("ABSENT")
    ABSENT, 
    @JsonProperty("LATE")
    LATE, 
    @JsonProperty("EXCUSED")
    EXCUSED
    
}
