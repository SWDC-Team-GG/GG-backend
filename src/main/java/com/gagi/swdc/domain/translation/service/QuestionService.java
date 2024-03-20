package com.gagi.swdc.domain.translation.service;

import com.gagi.swdc.domain.translation.domain.repository.TranslationRepository;
import com.gagi.swdc.domain.translation.presentation.dto.req.QuestionRequest;
import com.gagi.swdc.domain.translation.presentation.dto.res.AnswerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final TranslationRepository translationRepository;

    public AnswerResponse execute(QuestionRequest request) {
        translationRepository.save(request.toEntity());
        return makeAnswer();
    }

    private AnswerResponse makeAnswer() { return null; }
}
