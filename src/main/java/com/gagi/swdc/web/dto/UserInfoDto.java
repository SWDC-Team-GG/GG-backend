package com.gagi.swdc.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class UserInfoDto implements Serializable {
    private String userId;
    private String name;
    private Long scienceLevel;
    private Long humanitiesLevel;

    @Builder
    public UserInfoDto(String userId, String name, Long scienceLevel, Long humanitiesLevel) {
        this.userId = userId;
        this.name = name;
        this.scienceLevel = scienceLevel;
        this.humanitiesLevel = humanitiesLevel;
    }
}
