package com.caca.organizasyon.exception;

import org.springframework.http.HttpStatusCode;

public class ResourceNotFoundException extends BaseException {

    private ResourceNotFoundException(ErrorCode errorCode, String detail) {
        super(errorCode, detail, HttpStatusCode.valueOf(404));
    }

    public static ResourceNotFoundException of(ErrorCode errorCode) {
        return new ResourceNotFoundException(errorCode, null);
    }

    public static ResourceNotFoundException of(ErrorCode errorCode, String detail) {
        return new ResourceNotFoundException(errorCode, detail);
    }

}
