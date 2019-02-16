package io.lker.usermanagement.model.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthenticationRequest {
    private String email;
    private String password;

}