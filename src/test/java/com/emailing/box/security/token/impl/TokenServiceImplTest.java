package com.emailing.box.security.token.impl;

import com.emailing.box.security.token.Token;
import com.emailing.box.security.token.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
public class TokenServiceImplTest {

    @Autowired
    TokenService tokenService;

    @Test
    public void testNullToken() {
        Token token = tokenService.getJwtToken("alamaouj","admin");
        System.out.println(token);

        assertNotNull(token);
    }

}