package io.lker.webstore.controllers;

import io.lker.webstore.common.model.user.User;
import io.lker.webstore.usermanagement.util.SecurityConstants;
import io.lker.webstore.usermanagement.model.security.JwtAuthenticationRequest;
import io.lker.webstore.usermanagement.model.security.TokenHelper;
import io.lker.webstore.usermanagement.model.security.UserTokenState;
import io.lker.webstore.usermanagement.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class UserAuthentication {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenHelper tokenHelper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAuthentication(UserService userService,
                              AuthenticationManager authenticationManager,
                              TokenHelper tokenHelper,
                              BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenHelper = tokenHelper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest,
            HttpServletResponse response) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        String jws = tokenHelper.generateToken(user.getUsername());
        long expiresIn = SecurityConstants.EXPIRATION_DATE;
        return ResponseEntity.ok(new UserTokenState(jws, expiresIn));
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.save(user);
    }
}
