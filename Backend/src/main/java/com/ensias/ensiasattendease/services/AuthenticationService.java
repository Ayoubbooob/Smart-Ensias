package com.ensias.ensiasattendease.services;

import com.ensias.ensiasattendease.resources.RequestModels.AuthenticationRequest;
import com.ensias.ensiasattendease.resources.AuthenticationResponse;
import com.ensias.ensiasattendease.resources.RequestModels.StudentRegisterRequest;
import org.springframework.stereotype.Component;

public interface AuthenticationService {

//    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse registerStudent(StudentRegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
