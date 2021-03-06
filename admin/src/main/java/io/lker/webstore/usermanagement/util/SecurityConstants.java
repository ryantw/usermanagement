package io.lker.webstore.usermanagement.util;

import io.jsonwebtoken.SignatureAlgorithm;

public class SecurityConstants {
    public static final String APP_NAME = "USER_MANAGEMENT";
    // yeah yeah, this wouldn't be here if it as a real app.
    public static final String SECRET = "AardvarksAndJWTs";
    public static final long EXPIRATION_DATE = 900;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users/sign-up";
    public static final String AUDIENCE = "errbody";
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
}
