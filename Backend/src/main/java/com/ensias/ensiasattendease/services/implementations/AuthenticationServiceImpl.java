package com.ensias.ensiasattendease.services.implementations;

import com.ensias.ensiasattendease.config.JwtService;
import com.ensias.ensiasattendease.models.*;
import com.ensias.ensiasattendease.repositories.TokenRepository;
import com.ensias.ensiasattendease.repositories.UserRepository;
import com.ensias.ensiasattendease.resources.RequestModels.AuthenticationRequest;
import com.ensias.ensiasattendease.resources.AuthenticationResponse;
import com.ensias.ensiasattendease.resources.RequestModels.StudentRegisterRequest;
import com.ensias.ensiasattendease.resources.RequestModels.TeacherRegisterRequest;
import com.ensias.ensiasattendease.services.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserRepository userRepository;


    private final StudentServiceImpl studentService;

    private final TeacherServiceImpl teacherService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    private final TokenRepository tokenRepository;

    public AuthenticationResponse registerStudent(StudentRegisterRequest request) {

        Student student = studentService.registerStudent(request);

        var jwtToken = jwtService.generateToken(student);
        var refreshToken = jwtService.generateRefreshToken(student);

        saveUserToken(student, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role("Student")
                .build();
    }

    public AuthenticationResponse registerTeacher(TeacherRegisterRequest request) {

        Teacher teacher = teacherService.registerTeacher(request);

        var jwtToken = jwtService.generateToken(teacher);
        var refreshToken = jwtService.generateRefreshToken(teacher);

        saveUserToken(teacher, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role("Teacher")
                .build();
    }
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role(user.getRole())
                .build();

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    //TODO - We can use one method with testing on roles
//    @Override
//    public AuthenticationResponse register(RegisterRequest request) {

//            teacherModel = TeacherModel.builder()
//                    .first_name(request.getFirstname())
//                    .last_name(request.getLastname())
//                    .email(request.getEmail())
//                    .phone(request.getPhone())
//                    .password(passwordEncoder.encode(request.getPassword()))
////                .role(request.getRole())//TODO - fix roles
//                    .build();

//        var jwtToken = jwtService.generateToken(user);
//        System.out.printf("**************"+jwtToken);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }



}
