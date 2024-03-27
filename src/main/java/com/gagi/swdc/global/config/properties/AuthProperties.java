package com.gagi.swdc.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("auth")
public class AuthProperties {

    private Google google;

    @Getter
    @Setter
    public static class Google {
        private String baseUrl;
        private String clientId;
        private String redirectUrl;
    }

    public String getGoogleBaseUrl() {
        return google.getBaseUrl();
    }

    public String getGoogleClientId() {
        return google.getClientId();
    }

    public String getGoogleRedirectUrl() {
        return google.getRedirectUrl();
    }
}
