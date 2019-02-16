package io.lker.usermanagement.security.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.lker.usermanagement.model.User;
import io.lker.usermanagement.security.SecurityConstants;
import io.lker.usermanagement.util.TimeProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TokenHelper {

    private final TimeProvider timeProvider;

    public TokenHelper(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public String getEmailFromToken(String token){
        String email;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            email = claims.getSubject();
        } catch (Exception e){
            email = null;
            log.error("Email not found. "  + e.getMessage());
        }
        return email;
    }

    private Date getIssuedAtDateFromToken(String token){
        Date issuedAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issuedAt = claims.getIssuedAt();
        } catch (Exception e){
            issuedAt = null;
            log.error("Error getting when token was issued. " + e.getMessage());
        }
        return issuedAt;
    }

    public String getAudienceFromToken(String token){
        String audience;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e){
            audience = null;
            log.error("Error getting audience. " + e.getMessage());
        }
        return audience;
    }

    public String refreshToken(String token){
        String refreshedToken;
        Date now = TimeProvider.now();
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(now);
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SecurityConstants.SIGNATURE_ALGORITHM, SecurityConstants.SECRET)
                    .compact();
        } catch (Exception e){
            refreshedToken = null;
            log.error("Error generating refresh token: " + e.getMessage());
        }
        return refreshedToken;
    }

    public String generateToken(String email){
        return Jwts.builder()
                .setIssuer(SecurityConstants.APP_NAME)
                .setSubject(email)
                .setAudience(SecurityConstants.AUDIENCE)
                .setIssuedAt(TimeProvider.now())
                .setExpiration(generateExpirationDate())
                .signWith(SecurityConstants.SIGNATURE_ALGORITHM, SecurityConstants.SECRET)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        User user = (User) userDetails;
        final String email = getEmailFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
        // todo
        // validate created before last password reset
        return ( email != null && email.equals(user.getEmailAddress()));
    }

    private Date generateExpirationDate(){
        long expiresIn = SecurityConstants.EXPIRATION_DATE;
        return new Date(TimeProvider.now().getTime() + expiresIn * 1000);
    }

    private Claims getAllClaimsFromToken(String token){
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
            log.error("Claim not found. " + e.getMessage());
        }

        return claims;
    }
}
