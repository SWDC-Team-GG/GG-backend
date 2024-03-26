package com.gagi.swdc.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("clova")
public class ClovaProperties {
    private String api_key;
    private String gateway_key;
}
