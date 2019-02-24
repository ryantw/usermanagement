package io.lker.webstore.usermanagement.model.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthenticationRequest {
    private String username;
    private String password;

}