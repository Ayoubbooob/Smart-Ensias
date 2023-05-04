package com.ensias.ensiasattendease.services;

import com.ensias.ensiasattendease.resources.RequestModels.AuthenticationRequest;
import com.ensias.ensiasattendease.resources.responses.AuthenticationResponse;
import com.ensias.ensiasattendease.resources.RequestModels.StudentRegisterRequest;

public interface AuthenticationService {

//    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse registerStudent(StudentRegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
