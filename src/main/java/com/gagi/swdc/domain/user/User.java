package com.gagi.swdc.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User { // user 테이블 생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 아이디
    @Column(nullable = false)
    private String userId;

    // 이름
    @Column(nullable = false)
    private String name;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    @Column
    private Long scienceLevel;

    @Column
    private Long humanitiesLevel;

    @Builder
    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public void update(Long scienceLevel, Long humanitiesLevel) {
        this.scienceLevel = scienceLevel;
        this.humanitiesLevel = humanitiesLevel;
    }
}
