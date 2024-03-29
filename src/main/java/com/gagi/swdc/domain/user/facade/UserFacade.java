package com.gagi.swdc.domain.user.facade;

import com.gagi.swdc.domain.user.domain.User;
import com.gagi.swdc.domain.user.domain.repository.UserRepository;
import com.gagi.swdc.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@RequiredArgsConstructor
@Configuration
public class UserFacade {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(email);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public Optional<User> findEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
