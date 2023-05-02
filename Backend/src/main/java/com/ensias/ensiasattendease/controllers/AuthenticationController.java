package com.ensias.ensiasattendease.controllers;

import com.ensias.ensiasattendease.resources.RequestModels.AuthenticationRequest;
import com.ensias.ensiasattendease.resources.AuthenticationResponse;
import com.ensias.ensiasattendease.resources.RequestModels.StudentRegisterRequest;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRegisterRequest;
import com.ensias.ensiasattendease.services.implementations.AuthenticationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/register/teacher")
    public ResponseEntity<AuthenticationResponse> registerTeacher(@RequestBody TeacherRegisterRequest request){
        return ResponseEntity.ok(authenticationService.registerTeacher(request));

    }

    @PostMapping("/register/student")
    public ResponseEntity<AuthenticationResponse> registerStudent(
            @RequestBody StudentRegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.registerStudent(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody RegisterRequest request
//    ){
//
//        if(request.getRole().equals("Teacher")){
//
//        }
//        return ResponseEntity.ok(authenticationService.register(request));
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> registerss(
//            @RequestBody RegisterRequest request
//    ){
//
//
//        return ResponseEntity.ok(authenticationService.register(request));
//    }


}
