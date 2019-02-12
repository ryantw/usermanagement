package io.lker.usermanagement.security;

public class SecurityConstants {
    public static final String SECRET = "AardvarksAndJWTs";
    public static final long EXPIRATION_DATE = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users/sign-up";
}
