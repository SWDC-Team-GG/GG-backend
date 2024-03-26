package com.gagi.swdc.domain.translation.service;

import com.gagi.swdc.domain.translation.domain.repository.TranslationRepository;
import com.gagi.swdc.domain.translation.presentation.dto.req.QuestionRequest;
import com.gagi.swdc.domain.translation.presentation.dto.res.AnswerResponse;
import com.gagi.swdc.global.feign.clova.ClovaChatClient;
import com.gagi.swdc.global.feign.clova.dto.req.ClovaChatCompletionsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final TranslationRepository translationRepository;
    private final ClovaChatClient clovaChatClient;

    public AnswerResponse execute(QuestionRequest request) throws IOException {
        translationRepository.save(request.toEntity());
        return makeAnswer(request.getQuestion());
    }

    private AnswerResponse makeAnswer(String question) throws IOException {
        String request = ClovaChatCompletionsRequest.data(question);
        Object resposne = clovaChatClient.clovaChatCompletions(request);
        System.out.println(resposne);
        return null;
    }
}
