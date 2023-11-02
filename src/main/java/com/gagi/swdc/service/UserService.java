package com.gagi.swdc.service;

import com.gagi.swdc.domain.user.User;
import com.gagi.swdc.domain.user.UserRepository;
import com.gagi.swdc.web.dto.LevelDto;
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
    public Pair<User, Boolean> login(LoginDto loginDto) {
        String userId = loginDto.getUserId();
        String password = loginDto.getPassword();
        User user = userRepository.findByUserIdAndPassword(userId, password);

        if (user != null) return Pair.of(user, true);
        if (user == null) return Pair.of(user, false);
        throw new IllegalArgumentException();
    }

    @Transactional
    public User select(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));
    }

    @Transactional
    public void updateLevel(Long id, LevelDto levelDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));

        user.update(levelDto.getEducation(), levelDto.getField());
    }

    @Transactional
    public void updateSurvey(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));

        user.updateSurvey(true);
    }

    @Transactional
    public Boolean checkUserId(String userId) {
        return userRepository.findByUserId(userId) != null;
    }
}
