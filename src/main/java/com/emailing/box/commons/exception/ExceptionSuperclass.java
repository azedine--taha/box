package com.emailing.box.commons.exception;

import java.util.*;
import java.util.stream.Collectors;

public class ExceptionSuperclass extends Exception {

    private static final long serialVersionUID = 933222222399927513L;


    protected final transient Collection<Field> exceptionContext;

    private final HttpErrorCode httpCode;
    private final ExceptionType exceptionType;

    protected ExceptionSuperclass(HttpErrorCode httpCode, ExceptionType exceptionType, Exception ex, Field... context) {
        this(httpCode, exceptionType, ex, Arrays.asList(context));
    }

    protected ExceptionSuperclass(HttpErrorCode httpCode, ExceptionType exceptionType, Exception ex, Collection<Field> context) {
        super(buildMessage(context), ex);
        this.httpCode = httpCode;
        this.exceptionType = exceptionType;
        this.exceptionContext = context;
    }


    private static String buildMessage(Collection<Field> exceptionContext) {
        return exceptionContext.stream().map(f -> f.getKey() + ":" + f.getValue()).collect(Collectors.joining(", ", "[", "]"));
    }

    /** The exception type */
    public enum ExceptionType {
        BUSINESS("business"),
        TECHNICAL("technical");

        private String value;

        private ExceptionType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    /** HTTP error code that should be returned in case of failure */
    protected enum HttpErrorCode {
        UNAUTHORIZED(401),
        FORBIDDEN(403),
        NOT_FOUND(404),
       ;

        private int code;

        private HttpErrorCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    public abstract static class BuilderSuperclass<E extends ExceptionSuperclass, B extends BuilderSuperclass<E, B>> {
        private Exception e;
        private List<Field> context = new ArrayList<>();
        private ExceptionType type;

        protected BuilderSuperclass(ExceptionType type) {
            this.type = type;
        }

        @SuppressWarnings("unchecked")
        public B withCause(Exception e) {
            this.e = e;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B withType(ExceptionType type) {
            this.type = type;
            return (B) this;
        }

        public abstract E build();

        protected void addToContext(Field f) {
            this.context.add(f);
        }

        protected Exception getCause() {
            return this.e;
        }

        protected Field[] getContext() {
            return this.context.toArray(new Field[this.context.size()]);
        }

        public ExceptionType getExceptionType() {
            return this.type;
        }
    }
}

