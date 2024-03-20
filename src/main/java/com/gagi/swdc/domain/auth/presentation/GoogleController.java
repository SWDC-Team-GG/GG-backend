package com.gagi.swdc.domain.auth.presentation;

import com.gagi.swdc.domain.auth.presentation.dto.req.AccessTokenRequest;
import com.gagi.swdc.domain.auth.presentation.dto.res.TokenResponse;
import com.gagi.swdc.domain.auth.service.GoogleAuthLinkService;
import com.gagi.swdc.domain.auth.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/google")
@RestController
public class GoogleController {
    private final GoogleAuthLinkService googleLinkService;
    private final GoogleAuthService googleAuthService;

    @GetMapping
    public String getGoogleAuthLink() {
        return googleLinkService.execute();
    }

    @PostMapping
    public TokenResponse login(@RequestBody AccessTokenRequest accessTokenRequest) {
        return googleAuthService.execute(accessTokenRequest.getAccessToken());
    }
}
