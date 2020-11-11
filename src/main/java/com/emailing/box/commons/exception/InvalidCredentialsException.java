package com.emailing.box.commons.exception;

import com.sun.xml.internal.ws.api.model.ExceptionType;

public class InvalidCredentialsException extends UnauthorizedException {

    private static final long serialVersionUID = -9221547425839545282L;

    protected InvalidCredentialsException(ExceptionType exceptionType, Exception ex, Field... context) {
        super(exceptionType, ex, context);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends BuilderSuperclass<InvalidCredentialsException, Builder> {

        private Builder() {
            super(ExceptionType.BUSINESS);
        }

        public Builder withError(String error) {
            addToContext(new Field("error", error, true));
            return this;
        }


        @Override
        public InvalidCredentialsException build() {
            return new InvalidCredentialsException(getExceptionType(), getCause(), getContext());
        }
    }
}
