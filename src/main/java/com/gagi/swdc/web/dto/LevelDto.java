package com.gagi.swdc.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LevelDto {
    private int education;
    private float field;

    @Builder
    public LevelDto(int education, float field) {
        this.education = education;
        this.field = field;
    }
}
