package com.emailing.box.security.token;

public interface TokenService {

    Token getJwtToken(String login,String role);
}
