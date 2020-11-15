package com.emailing.box.security.token;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String BEARER = "Bearer ";
    private static final long EXPIRATION_TIME =864 ;
    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).toInstant().plus(this.EXPIRATION_TIME, ChronoUnit.SECONDS).isBefore(Instant.now());
    }

    public Token generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private Token createToken(Map<String, Object> claims, String subject) {

        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        Date now = Date.from(ldt.atZone(ZoneOffset.UTC).toInstant());

        return  new Token(Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(now.toInstant().plus(EXPIRATION_TIME, ChronoUnit.SECONDS)))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact());
    }

    public Boolean validateToken(String token, String username) {
         token = token.substring(BEARER.length()).trim();
        String s = extractUsername(token);
        boolean b = s.equals(username) && !isTokenExpired(token);
        return b;
    }
}
