package com.gagi.swdc.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class UserInfoDto implements Serializable {
    private Long id;
    private String userId;
    private String name;
    private int education;
    private float field;
    private Boolean survey;

    @Builder
    public UserInfoDto(Long id, String userId, String name, int education, float field, Boolean survey) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.education = education;
        this.field = field;
        this.survey = survey;
    }
}
