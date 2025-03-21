package com.caca.organizasyon.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppConfig {

    @Value("${app.file.upload-dir}")
    private String uploadDir;
}
