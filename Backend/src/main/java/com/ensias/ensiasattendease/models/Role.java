package com.ensias.ensiasattendease.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    // ADD HERE PERMISSIONS OF AN USER, LIKE AN ADMIN CAN READ, UPDATE, DELETE, CREATE FROM & IN ATTENDANCE RESOURCE


                    //attendance
                    Permission.ATTENDANCE_READ,
                    Permission.ATTENDANCE_UPDATE,
                    Permission.ATTENDANCE_DELETE,
                    Permission.ATTENDANCE_CREATE,


                    //course
                    Permission.COURSE_READ,
                    Permission.COURSE_UPDATE,
                    Permission.COURSE_CREATE,
                    Permission.COURSE_DELETE,

                    //event
                    Permission.EVENT_DELETE,
                    Permission.EVENT_UPDATE,
                    Permission.EVENT_READ,
                    Permission.EVENT_CREATE,

                    //student
                    Permission.STUDENT_READ,
                    Permission.STUDENT_UPDATE,
                    Permission.STUDENT_DELETE,
                    Permission.STUDENT_CREATE,
                    Permission.STUDENT_PATCH,


                    //holiday
                    Permission.HOLIDAY_CREATE,
                    Permission.HOLIDAY_UPDATE,
                    Permission.HOLIDAY_DELETE,
                    Permission.HOLIDAY_READ,


                    //justification
                    Permission.JUSTIFICATION_UPDATE,
                    Permission.JUSTIFICATION_READ,
                    Permission.JUSTIFICATION_DELETE,
                    Permission.JUSTIFICATION_CREATE,

                    //filiere
                    Permission.FILIERE_UPDATE,
                    Permission.FILIERE_DELETE,
                    Permission.FILIERE_READ,
                    Permission.FILIERE_CREATE

            )
    ),

    STUDENT(
            Set.of(
                    Permission.FILIERE_READ,
                    Permission.JUSTIFICATION_READ,
                    Permission.HOLIDAY_READ,
                    Permission.STUDENT_READ,
                    Permission.COURSE_READ,
                    Permission.ATTENDANCE_READ,
                    Permission.EVENT_READ
                    )
    ),


    TEACHER(
            Set.of(
                    Permission.FILIERE_READ,
                    Permission.JUSTIFICATION_READ,
                    Permission.HOLIDAY_READ,
                    Permission.STUDENT_READ,
                    Permission.COURSE_READ,
                    Permission.ATTENDANCE_READ,
                    Permission.EVENT_READ

            )
    )
    ;


    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}