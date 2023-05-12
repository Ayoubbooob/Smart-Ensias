package com.ensias.ensiasattendease.services.implementations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.ensias.ensiasattendease.models.StudentModel;
import com.ensias.ensiasattendease.resources.RequestModels.RegisterRequest;
import com.ensias.ensiasattendease.resources.RequestModels.StudentRegisterRequest;
import com.ensias.ensiasattendease.resources.responses.AuthenticationResponse;
import com.ensias.ensiasattendease.resources.responses.StudentResponse;
import com.ensias.ensiasattendease.services.AuthenticationService;
import com.ensias.ensiasattendease.services.StudentService;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor
public class StudentRegistrationServiceImpl{

    @Autowired
    private AuthenticationService  studentService;

    @Test
    public void itShouldRegisterStudent() {
        /**
         * setup test env 
         */
        StudentRegisterRequest request = new StudentRegisterRequest(); 
        request.setCne("20191173");
        request.setEmail("teste@gmail.com");
        request.setGender("MALE");
        request.setFirstname("testFirstName");
        request.setLastname("testLastName");
        request.setPassword("12345678");
        request.setPhone("0612345678"); 
        request.setDate_of_birth("1999-08-17");
        /**
         * when
         */
        AuthenticationResponse response = studentService.registerStudent(request);

        /**
         * assert
         * 
         */
        assert(response != null);
        assert(response.getAccessToken() != null);
        assert(response.getRefreshToken() != null);
        assert(response.getRole() != null) ; 
        assert(response.getRole().equals("STUDENT"));

    }

    
}
