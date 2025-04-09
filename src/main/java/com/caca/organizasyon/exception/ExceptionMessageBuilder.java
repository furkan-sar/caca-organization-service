package com.caca.organizasyon.exception;

import org.springframework.util.StringUtils;

public class ExceptionMessageBuilder {

    private ExceptionMessageBuilder() {

    }

    protected static String buildMessage(ErrorCode code, String detail) {
        if (code == null) {
            throw ServerException.of(ErrorCode.GENERAL_EXCEPTION, "Hata mesajı oluşturulamadı.");
        }

        String baseMessage = code.getDescription();
        StringBuilder message = new StringBuilder(baseMessage);

        if (StringUtils.hasText(detail)) {
            message.append(": ").append(detail.trim());
        }

        return message.toString();
    }
}
