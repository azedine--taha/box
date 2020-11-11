package com.emailing.box.commons.exception;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Field {
    private final String key;
    private final String value;
    private final boolean confidential;

    public Field(String key, Boolean value, boolean confidential) {
        this(key, value != null ? value.toString() : null, confidential);
    }

    public Field(String key, Long value, boolean confidential) {
        this(key, value != null ? value.toString() : null, confidential);
    }

    public Field(String key, Integer value, boolean confidential) {
        this(key, value != null ? value.toString() : null, confidential);
    }

    public Field(String key, Date value, boolean confidential) {
        this(key, value != null ? value.toString() : null, confidential);
    }

    public Field(String key, Calendar value, boolean confidential) {
        this(key, value != null ? value.toString() : null, confidential);
    }

    public Field(String key, Instant value, boolean confidential) {
        this(key, value != null ? value.toString() : null, confidential);
    }

    public Field(String key, List<?> value, boolean confidential) {
        this(key, value != null ? value.toString() : null, confidential);
    }

    public Field(String key, String value, boolean confidential) {
        if (key != null) {
            this.key = key;
            this.value = value;
            this.confidential = confidential;
        } else {
            throw new IllegalArgumentException("A valid key must be provided");
        }
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isConfidential() {
        return confidential;
    }

    public boolean isNotConfidential() {
        return !isConfidential();
    }
}
