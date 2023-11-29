package com.gagi.swdc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class corsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://localhost:3000", "https://web-gg-frontend-5mk12aloze1mre.sel5.cloudtype.app/", "http://localhost:5000", "https://swdc.anys34.com")
                .allowedMethods("*")
                .allowCredentials(true);
    }
}