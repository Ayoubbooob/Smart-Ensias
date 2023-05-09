package com.ensias.ensiasattendease.services.implementations;

import com.ensias.ensiasattendease.models.GenreUser;
import com.ensias.ensiasattendease.models.TeacherModel;
import com.ensias.ensiasattendease.models.Role;
import com.ensias.ensiasattendease.models.TeacherModel;
import com.ensias.ensiasattendease.repositories.TeacherRepository;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRegisterRequest;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRequestResponse;
import com.ensias.ensiasattendease.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
                .role(Role.TEACHER)
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

    
//

    public List<TeacherRequestResponse> getAllTeachers() {
        List<TeacherModel> teachers =  teacherRepository.findAll();
        List<TeacherRequestResponse> teachersResponses = new ArrayList<>();
        for(TeacherModel teacher : teachers){
            TeacherRequestResponse response = TeacherRequestResponse.builder()
                .firstname(teacher.getFirst_name())
                .lastname(teacher.getLast_name())
                .email(teacher.getEmail())
                .phone(teacher.getPhone())
                .date_of_birth(teacher.getDate_of_birth().toString())
//                .image_url(teacher.getImage_url())
                .gender(String.valueOf(teacher.getGenre()))
                .matricule(teacher.getMatricule())
                .joining_date(teacher.getJoining_date().toString())
                .build();
            teachersResponses.add(response);
        }
        return teachersResponses;

    }

    public TeacherRequestResponse getTeacherById(Long teacherId) {
        TeacherModel teacher = teacherRepository.findById(teacherId).orElse(null);

        if(teacher ==  null) return null;

        return TeacherRequestResponse.builder()
                .firstname(teacher.getFirst_name())
                .lastname(teacher.getLast_name())
                .email(teacher.getEmail())
                .phone(teacher.getPhone())
                .date_of_birth(teacher.getDate_of_birth().toString())
//                .image_url(teacher.getImage_url())
                .gender(String.valueOf(teacher.getGenre()))
                .matricule(teacher.getMatricule())
                .joining_date(teacher.getJoining_date().toString())
                .build();
    }


    public TeacherModel patchTeacher(Long teacherId, Map<String, Object> updates) throws Exception {
        Optional<TeacherModel> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isEmpty()) {
            throw new Exception("Teacher not found with id: " + teacherId);
        }
        TeacherModel existingTeacher = optionalTeacher.get();

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            switch (field) {
                case "first_name" -> existingTeacher.setFirst_name(value.toString());
                case "last_name" -> existingTeacher.setLast_name(value.toString());
                case "email" -> existingTeacher.setEmail(value.toString());
                case "phone" -> existingTeacher.setPhone(value.toString());
                case "genre" -> existingTeacher.setGenre(GenreUser.valueOf(value.toString()));
                case "date_of_birth" -> existingTeacher.setDate_of_birth(LocalDate.parse(value.toString()));
                case "matricule" -> existingTeacher.setMatricule(value.toString());
                case "joining_date" -> existingTeacher.setJoining_date(LocalDate.parse(value.toString()));
                case "password" -> existingTeacher.setPassword(value.toString());
                case "image_url" -> existingTeacher.setImage_url(value.toString());
                case "role" -> existingTeacher.setRole(Role.valueOf(value.toString()));


                default -> throw new IllegalArgumentException("Invalid field: " + field);
            }
        }

        teacherRepository.save(existingTeacher);
        return existingTeacher;
    }
//    public TeacherModel updateTeacher(Long teacherId, AddTeacherRequest AddTeacherRequest) {
//    }


    public void deleteTeacher(Long teacherId) throws Exception {
        Optional<TeacherModel> teacher = teacherRepository.findById(teacherId);
        if (teacher.isPresent()) {
            teacherRepository.delete(teacher.get());
        } else {
            throw new Exception("Teacher with ID " + teacherId + " not found");
        }
    }




  //  We can use this methode if we want to create a teacher in our system , not account
//
//    public TeacherModel createHoliday(AddTeacherRequest addTeacherRequest){
//
//        TeacherModel TeacherModelToSave = TeacherModel.builder()
//                .first_name(addTeacherRequest.getFirstname())
//                .last_name(addTeacherRequest.getLastname())
//                .email(addTeacherRequest.getEmail())
//                .phone(addTeacherRequest.getPhone())
//                .date_of_birth(LocalDate.parse(addTeacherRequest.getDate_of_birth()))
//                .image_url(addTeacherRequest.getImage_url())
//                .genre(GenreUser.valueOf(addTeacherRequest.getGender()))
//                .matricule(addTeacherRequest.getMatricule())
//                .joining_date(LocalDate.parse(addTeacherRequest.getJoining_date()))
//                .build();
//        teacherRepository.save(TeacherModelToSave);
//        return TeacherModelToSave;
//
//    }



    //Update all Teacher fields , you have to put all teache field to a successful operation
//    public TeacherModel updateTeacher(Long teacherId, AddTeacherRequest AddTeacherRequest) throws Exception {
//        Optional<TeacherModel> optionalTeacher = teacherRepository.findById(teacherId);
//        if (optionalTeacher.isEmpty()) {
//            throw new Exception("Holiday not found with id: " + teacherId);
//        }
//        TeacherModel existingTeacher = optionalTeacher.get();
//        existingTeacher.setStart_date(LocalDate.parse(AddTeacherRequest.getStart_date()));
//        existingTeacher.setEnd_date(LocalDate.parse(AddTeacherRequest.getEnd_date()));
//        existingTeacher.setTitle(AddTeacherRequest.getTitle());
//        existingTeacher.setType(AddTeacherRequest.getType());
//        teacherRepository.save(existingTeacher);
//        return existingTeacher;
//    }
//
}
