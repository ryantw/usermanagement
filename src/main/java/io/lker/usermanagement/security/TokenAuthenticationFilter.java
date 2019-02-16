package io.lker.usermanagement.security;

import io.lker.usermanagement.security.model.TokenHelper;
import io.lker.usermanagement.services.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private TokenHelper tokenHelper;
    private UserAuthService userAuthService;

    public TokenAuthenticationFilter(TokenHelper tokenHelper, UserAuthService userAuthService) {
        this.tokenHelper = tokenHelper;
        this.userAuthService = userAuthService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String email;
        String authToken = httpServletRequest.getHeader(SecurityConstants.HEADER_STRING);

        if(authToken != null){
            authToken = authToken.replace(SecurityConstants.TOKEN_PREFIX, "");
            email = tokenHelper.getEmailFromToken(authToken);
            if(email != null){
                UserDetails userDetails = userAuthService.loadUserByUsername(email);
                if(tokenHelper.validateToken(authToken, userDetails)){
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
