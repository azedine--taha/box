package com.emailing.box.security.token.impl;

import com.emailing.box.security.token.ValidToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class RequestHeaderValidaor {

    private static final String SECRET = "SecretKeyToGenJWTs";
    private static final String BEARER = "Bearer ";
    private static final String BASIC = "Basic ";
    private static final String AUDIENCE = "audience";
    public static final long EXPIRATION_TIME = 864000000; // 10 days

    private ValidToken valideToken;

    public boolean isValidToken (String jwtToken){
        try {

           String token = jwtToken.substring(BEARER.length()).trim();
            this.valideToken = new ValidToken(this.validateAndParseClaims(token));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    private Jws<Claims> validateAndParseClaims(String jwtToken) throws Exception{
        try {
            // Parse the token
             Jws<Claims> parsedClaims = Jwts.parser().setSigningKey(this.SECRET).parseClaimsJws(jwtToken);
            // Validate the claims
            validateClaims(parsedClaims);
            // The claims are valid
            return parsedClaims;
        } catch (Exception exc) {
            throw new Exception("souciiiiii token ");
        }
    }

    private void validateClaims(final Jws<Claims> claims) throws Exception {
        // 1. The token must have been signed with HMAC512
        validateAlgorithm(claims.getHeader().getAlgorithm());
        // 2. The audience must match the expected one
        validateAudience(claims.getBody().getAudience());
        // 4. The token must not have an issued date in the future and must not be expired
        validateTokenExpiration(claims.getBody().getIssuedAt(), claims.getBody().getExpiration());
    }

    private void validateAlgorithm(String algorithm) throws Exception {
        if (!SignatureAlgorithm.HS512.equals(SignatureAlgorithm.forName(algorithm))) {
            throw new Exception("signatureAlgorithm");
        }
    }

    private void validateAudience(String audience) throws Exception {
        if (audience == null || !this.AUDIENCE.equals(audience)) {
            throw new Exception("Audience not correct");
        }
    }


    private void validateTokenExpiration(Date issuedAt, Date expirationDate) throws Exception {
        if (isTimestampExpired(issuedAt)) {
            throw new Exception("expiration ");
        }
    }

    private boolean isTimestampExpired(Date iat) {
        return iat.toInstant().plus(this.EXPIRATION_TIME, ChronoUnit.SECONDS).isBefore(Instant.now());
    }

    private boolean isExpirationCorrect(Date iat, Date exp) {
        Instant iatInstant = iat.toInstant().truncatedTo(ChronoUnit.SECONDS);
        Instant expInstant = exp.toInstant().truncatedTo(ChronoUnit.SECONDS);

        long actualExpiration = (expInstant.getLong(ChronoField.INSTANT_SECONDS) - iatInstant.getLong(ChronoField.INSTANT_SECONDS));
        long expectedExpiration = this.EXPIRATION_TIME;

        return actualExpiration == expectedExpiration;
    }

}
