package com.emailing.box.security.token;

import java.io.Serializable;

public class Token implements Serializable {

    private final String value;
    private final String type;

    public Token(String value) {
        this.value = value;
        this.type = "BASIC";
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }


}
