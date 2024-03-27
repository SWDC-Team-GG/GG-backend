package com.gagi.swdc.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.gagi.swdc.global.feign")
public class FeignConfig {
}
