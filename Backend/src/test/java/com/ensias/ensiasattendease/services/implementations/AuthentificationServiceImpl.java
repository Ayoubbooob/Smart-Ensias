//package com.ensias.ensiasattendease.services.implementations;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.ensias.ensiasattendease.resources.RequestModels.AuthenticationRequest;
//import com.ensias.ensiasattendease.resources.responses.AuthenticationResponse;
//import com.ensias.ensiasattendease.services.AuthenticationService;
//
//import lombok.RequiredArgsConstructor;
//
//// @DataJpaTest
//// @RunW(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@RequiredArgsConstructor
//public class AuthentificationServiceImpl {
//
//    @Autowired
//    private  AuthenticationService authenticationService ;
//
//    @Test
//    public void itShouldAuthenticateTeacher(){
//        AuthenticationRequest request = AuthenticationRequest.builder()
//                .email("Kajje1213@dd.com")
//                .password("11112345678")
//                .build();
//        AuthenticationResponse response = authenticationService.authenticate(request);
//        Assertions.assertThat(response).isNotNull();
//        Assertions.assertThat(response.getAccessToken()).isNotNull();
//        Assertions.assertThat(response.getRefreshToken()).isNotNull();
//        Assertions.assertThat(response.getId()).isNotNull();
//        Assertions.assertThat(response.getRole()).isEqualTo("STUDENT");
//    }
//
//}
