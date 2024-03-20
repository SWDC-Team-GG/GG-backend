package com.gagi.swdc.domain.auth.service;

import com.gagi.swdc.domain.auth.domain.RefreshToken;
import com.gagi.swdc.domain.auth.domain.repository.RefreshTokenRepository;
import com.gagi.swdc.domain.auth.presentation.dto.res.AccessTokenResponse;
import com.gagi.swdc.global.security.jwt.JwtTokenProvider;
import com.gagi.swdc.global.security.jwt.exception.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateAccessTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public AccessTokenResponse execute(String token) {
        RefreshToken refreshToken = getRefreshToken(token);
        return new AccessTokenResponse(jwtTokenProvider
                .createAccessToken(refreshToken.getEmail()));
    }

    private RefreshToken getRefreshToken(String token) {
        return refreshTokenRepository.findById(token)
                .orElseThrow(() -> ExpiredJwtException.EXCEPTION);
    }
}
