package com.gagi.swdc.domain.auth.service;

import com.gagi.swdc.domain.auth.presentation.dto.res.TokenResponse;
import com.gagi.swdc.domain.user.domain.User;
import com.gagi.swdc.domain.user.domain.repository.UserRepository;
import com.gagi.swdc.domain.user.facade.UserFacade;
import com.gagi.swdc.global.feign.auth.GoogleInformationClient;
import com.gagi.swdc.global.feign.auth.res.GoogleInformationResponse;
import com.gagi.swdc.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {
    private final GoogleInformationClient googleInformationClient;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    @Transactional
    public TokenResponse execute(String accessToken) {
        GoogleInformationResponse response = googleInformationClient
                .getUserInformation(accessToken);
        String email = response.getEmail();

        Optional<User> user = userFacade.findEmail(email);

        if (user.isEmpty()) {
            userRepository.save(User.builder()
                    .email(email)
                    .nickname(response.getName())
                    .profileImg(response.getPicture())
                    .build());
        }

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(email))
                .refreshToken(jwtTokenProvider.createRefreshToken(email))
                .build();
    }
}
