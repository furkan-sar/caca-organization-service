package com.caca.organizasyon.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AppProperties {

    @Value("${app.upload-dir}")
    private String uploadDir;

}
