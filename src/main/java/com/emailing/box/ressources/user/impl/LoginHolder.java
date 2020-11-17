package com.emailing.box.ressources.user.impl;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class LoginHolder {

    private static final String BASIC = "Basic ";
    private String login;
    private String password;

    public LoginHolder(String auth) {

        byte [] decodeHeader;

        decodeHeader = Base64.getUrlDecoder().decode(auth.substring(BASIC.length()).getBytes(StandardCharsets.UTF_8));

        if(decodeHeader !=null && decodeHeader.length !=0) {
            int idx = -1;
            for(int i = 0 ; i <decodeHeader.length ; i++ ) {
                if(decodeHeader [i]  == ':'){
                    idx = i;
                    break;
                }
            }

            if(idx != -1){
                this.login = new String(Arrays.copyOf(decodeHeader,idx),StandardCharsets.UTF_8);
                this.password = new String(decodeHeader,StandardCharsets.UTF_8).substring(login.length()+1);
            }

            if(this.login.trim().isEmpty()) {
                this.login = null;
            }

            if(this.password.trim().isEmpty()) {
                this.password = null;
            }
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


}
