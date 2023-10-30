package com.gagi.swdc.web.dto;

import com.gagi.swdc.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInDto {
    private String userId;
    private String name;
    private String password;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .name(name)
                .password(password)
                .build();
    }
}
