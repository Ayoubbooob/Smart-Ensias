package com.ensias.ensiasattendease.services.implementations;

import com.ensias.ensiasattendease.models.GenreUser;
import com.ensias.ensiasattendease.models.TeacherModel;
import com.ensias.ensiasattendease.repositories.TeacherRepository;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRegisterRequest;
import com.ensias.ensiasattendease.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;
    public TeacherModel registerTeacher(TeacherRegisterRequest request) {
        System.out.println("********** Password : "+request.getPassword());
        TeacherModel teacher = TeacherModel.builder()
                .first_name(request.getFirstname())
                .last_name(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
//                .password(request.getPassword())
                .role("Teacher")
                .email(request.getEmail())
                .phone(request.getPhone())
                .date_of_birth(LocalDate.parse(request.getDate_of_birth()))
                .image_url(request.getImage_url())
                .genre(GenreUser.valueOf(request.getGender()))
                .matricule(request.getMatricule())
                .joining_date(LocalDate.parse(request.getJoining_date()))
                .build();
        teacherRepository.save(teacher);
        return teacher;
    }

}
