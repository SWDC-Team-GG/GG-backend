package com.gagi.swdc.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LevelDto {
    private String education;
    private float field;

    @Builder
    public LevelDto(String education, float field) {
        this.education = education;
        this.field = field;
    }
}
