package com.gagi.swdc.domain.auth.presentation;

import com.gagi.swdc.domain.auth.presentation.dto.req.RefreshTokenRequest;
import com.gagi.swdc.domain.auth.presentation.dto.res.AccessTokenResponse;
import com.gagi.swdc.domain.auth.service.CreateAccessTokenService;
import com.gagi.swdc.domain.auth.service.DeleteRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final CreateAccessTokenService createNewAccessToken;
    private final DeleteRefreshTokenService deleteRefreshTokenService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccessTokenResponse createNewAccessToken(@RequestBody RefreshTokenRequest request) {
        return createNewAccessToken.execute(request.getRefreshToken());
    }

    @PostMapping("/logout")
    public void logout(@RequestBody RefreshTokenRequest request) {
        deleteRefreshTokenService.execute(request);
    }
}
