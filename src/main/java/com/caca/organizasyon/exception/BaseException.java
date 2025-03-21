package com.caca.organizasyon.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.util.StringUtils;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final ErrorCode errorCode;

    private final HttpStatusCode httpStatusCode;

    protected BaseException(ErrorCode errorCode, String detail, HttpStatusCode httpStatusCode) {
        super(buildMessage(errorCode, detail));

        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
    }

    private static String buildMessage(ErrorCode code, String detail) {
        if (code == null) {
            throw new IllegalArgumentException();
        }

        String baseMessage = code.getDescription();
        StringBuilder message = new StringBuilder(baseMessage);

        if (StringUtils.hasText(detail)) {
            message.append(": ").append(detail.trim());
        }

        return message.toString();
    }

    protected BaseException(ErrorCode errorCode, HttpStatusCode httpStatusCode) {
        this(errorCode, null, httpStatusCode);
    }

}