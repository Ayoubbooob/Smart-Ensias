package com.ensias.ensiasattendease.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    //Add here permissions,

    //attendance resource , Here Admin can Read, Update, Create, Delete this resource
    ATTENDANCE_READ("attendance:read"),
    ATTENDANCE_UPDATE("attendance:update"),
    ATTENDANCE_CREATE("attendance:create"),
    ATTENDANCE_DELETE("attendance:delete"),


    //courSe resource , Here Admin can Read, Update, Create, Delete this resource

    COURSE_READ("course:read"),
    COURSE_UPDATE("course:update"),
    COURSE_CREATE("course:create"),
    COURSE_DELETE("course:delete"),

    // EVENT RESOURCE
    EVENT_READ("event:read"),
    EVENT_UPDATE("event:update"),
    EVENT_CREATE("event:create"),
    EVENT_DELETE("event:delete"),


    // FILERE RESOURCE
    FILIERE_READ("filiere:read"),
    FILIERE_UPDATE("filiere:update"),
    FILIERE_CREATE("filiere:create"),
    FILIERE_DELETE("filiere:delete"),


    // JUSTIFICATION RESOURCE
    JUSTIFICATION_READ("justification:read"),
    JUSTIFICATION_UPDATE("filiere:update"),
    JUSTIFICATION_CREATE("filiere:create"),
    JUSTIFICATION_DELETE("filiere:delete"),


    // HOLIDAY RESOURCE
    HOLIDAY_READ("holiday:read"),
    HOLIDAY_UPDATE("holiday:update"),
    HOLIDAY_CREATE("holiday:create"),
    HOLIDAY_DELETE("holiday:delete"),


    // STUDENT RESOURCE
    STUDENT_READ("student:read"),
    STUDENT_UPDATE("student:update"),
    STUDENT_CREATE("student:create"),
    STUDENT_DELETE("student:delete"),
    STUDENT_PATCH("student:patch");
    @Getter
    private final String permission;

}