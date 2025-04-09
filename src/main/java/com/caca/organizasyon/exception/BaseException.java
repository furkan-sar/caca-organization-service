package com.caca.organizasyon.exception;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final ErrorCode errorCode;

    private final HttpStatusCode httpStatusCode;

    protected BaseException(ErrorCode errorCode, String detail, HttpStatusCode httpStatusCode) {
        super(ExceptionMessageBuilder.buildMessage(errorCode, detail));

        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
    }

    protected BaseException(ErrorCode errorCode, HttpStatusCode httpStatusCode) {
        this(errorCode, null, httpStatusCode);
    }


}