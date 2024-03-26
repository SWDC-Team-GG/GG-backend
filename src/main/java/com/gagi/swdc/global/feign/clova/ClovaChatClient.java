package com.gagi.swdc.global.feign.clova;

import com.gagi.swdc.global.config.HeaderConfig;
import com.gagi.swdc.global.feign.clova.dto.req.ClovaChatCompletionsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "ClovaChatCompletions",
        url = "https://clovastudio.apigw.ntruss.com/testapp/v1/chat-completions",
        configuration = {HeaderConfig.class})
public interface ClovaChatClient {
    @PostMapping(value = "/HCX-003")
    Map<String, Object> clovaChatCompletions(@RequestBody String request);
}
