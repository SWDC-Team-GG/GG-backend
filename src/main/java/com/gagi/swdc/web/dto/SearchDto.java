package com.gagi.swdc.web.dto;

import com.gagi.swdc.domain.search.Search;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class SearchDto {
    private Long userId;
    private String meaning;
    private String part;
    private String plainWord;
    private String translateWord;
    private String translateWordLevel;

    public Search toEntity() {
        return Search.builder()
                .userId(userId)
                .meaning(meaning)
                .part(part)
                .plainWord(plainWord)
                .translateWord(translateWord)
                .translateWordLevel(translateWordLevel)
                .build();
    }
}
