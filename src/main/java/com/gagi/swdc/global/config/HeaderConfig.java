package com.gagi.swdc.global.config;

import com.gagi.swdc.global.config.properties.ClovaProperties;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class HeaderConfig {
    private final ClovaProperties clovaProperties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-NCP-CLOVASTUDIO-API-KEY", clovaProperties.getApi_key());
            requestTemplate.header("X-NCP-APIGW-API-KEY", clovaProperties.getGateway_key());
            requestTemplate.header("Content-Type", "application/json");
        };
    }
}
