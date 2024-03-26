package com.gagi.swdc.domain.translation.presentation.dto.req;

import com.gagi.swdc.domain.translation.domain.Translation;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionRequest {
    private String question;

    public Translation toEntity(String answer) {
        return new Translation(question, answer);
    }
}
