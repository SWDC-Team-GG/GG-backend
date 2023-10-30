package com.gagi.swdc.service;

import com.gagi.swdc.domain.user.UserRepository;
import com.gagi.swdc.web.dto.SignInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long signIn(SignInDto signInDto) {
        return userRepository.save(signInDto.toEntity()).getId();
    }
}
