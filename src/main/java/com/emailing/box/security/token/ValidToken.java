package com.emailing.box.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;

public class ValidToken {

    private final Jws<Claims> claims;

    public ValidToken(Jws<Claims> claims) {
        this.claims = claims;
    }

    public String getAlgo(){
        return  getHeader().getAlgorithm();
    }

    public Claims getBody() {
        return this.claims.getBody();
    }

    protected <T extends JwsHeader<T>> JwsHeader<T> getHeader() {
        return this.claims.getHeader();
    }
}
