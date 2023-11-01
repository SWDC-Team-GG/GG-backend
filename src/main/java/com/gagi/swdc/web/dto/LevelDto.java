package com.gagi.swdc.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LevelDto {
    private Long scienceLevel;
    private Long humanitiesLevel;

    @Builder
    public LevelDto(Long scienceLevel, Long humanitiesLevel) {
        this.scienceLevel = scienceLevel;
        this.humanitiesLevel = humanitiesLevel;
    }
}
