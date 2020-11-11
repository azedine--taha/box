package com.emailing.box.commons.exception;

import com.sun.xml.internal.ws.api.model.ExceptionType;

public class UnauthorizedException extends ExceptionSuperclass {

    private static final long serialVersionUID = 3166995778242381622L;

    public UnauthorizedException(ExceptionType exceptionType, Exception ex, Field... context) {
        super(HttpErrorCode.UNAUTHORIZED, exceptionType, ex, context);
    }
}

