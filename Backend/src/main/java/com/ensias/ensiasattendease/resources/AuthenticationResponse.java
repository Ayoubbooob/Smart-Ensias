package com.ensias.ensiasattendease.resources;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty(namespace = "access_token")
    private String accessToken;

    @JsonProperty(namespace = "refresh_token")
    private String refreshToken;

    private String role;
}
