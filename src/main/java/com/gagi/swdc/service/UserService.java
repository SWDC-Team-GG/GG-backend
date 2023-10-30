package com.gagi.swdc.service;

import com.gagi.swdc.domain.user.User;
import com.gagi.swdc.domain.user.UserRepository;
import com.gagi.swdc.web.dto.LoginDto;
import com.gagi.swdc.web.dto.SignInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
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

    @Transactional
    public Pair<String, Boolean> login(LoginDto loginDto) {
        String userId = loginDto.getUserId();
        String password = loginDto.getPassword();
        User user = userRepository.findByUserIdAndPassword(userId, password);
        if (user != null) return Pair.of("로그인 성공", true);
        if (user == null) return Pair.of("로그인 실패", false);
        throw new IllegalArgumentException();
    }
}
