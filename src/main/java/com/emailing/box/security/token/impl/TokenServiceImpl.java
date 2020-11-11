package com.emailing.box.security.token.impl;

import com.emailing.box.security.token.Token;
import com.emailing.box.security.token.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Service
public class TokenServiceImpl implements TokenService {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days


    @Override
    public Token getJwtToken(String login, String role) {

        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        Date now = Date.from(ldt.atZone(ZoneOffset.UTC).toInstant());

        String value = Jwts.builder()
                .claim("role", role)
                .setAudience("audience")
                .setSubject(login)
                .setIssuedAt(now)
                .setExpiration(Date.from(now.toInstant().plus(EXPIRATION_TIME, ChronoUnit.SECONDS)))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();

        return new Token(value);
    }
}
