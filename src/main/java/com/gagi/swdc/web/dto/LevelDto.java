package com.gagi.swdc.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LevelDto {
    private String education;
    private String field;

    @Builder
    public LevelDto(String education, String field) {
        this.education = education;
        this.field = field;
    }
}
