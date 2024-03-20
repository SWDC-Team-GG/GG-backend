package com.gagi.swdc.domain.auth.service;

import com.gagi.swdc.domain.auth.domain.repository.RefreshTokenRepository;
import com.gagi.swdc.domain.auth.presentation.dto.req.RefreshTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteRefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public void execute(RefreshTokenRequest request) {
        refreshTokenRepository.deleteById(request.getRefreshToken());
    }
}
