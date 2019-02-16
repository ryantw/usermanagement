package io.lker.usermanagement.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthenticationRequest {
    private String email;
    private String password;

}