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
    private String education;
    private String field;
    private Boolean survey;

    @Builder
    public UserInfoDto(Long id, String userId, String name, String education, String field, Boolean survey) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.education = education;
        this.field = field;
        this.survey = survey;
    }
}
