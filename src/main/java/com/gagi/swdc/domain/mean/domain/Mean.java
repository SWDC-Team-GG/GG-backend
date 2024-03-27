package com.gagi.swdc.domain.mean.domain;

import com.gagi.swdc.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Entity
public class Mean {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    private String mean;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Mean(String word, String mean, User user) {
        this.word = word;
        this.mean = mean;
        this.user = user;
    }
}
