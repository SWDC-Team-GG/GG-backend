package com.gagi.swdc.domain.translation.service;

import com.gagi.swdc.domain.mean.domain.Mean;
import com.gagi.swdc.domain.mean.domain.repository.MeanRepository;
import com.gagi.swdc.domain.translation.domain.repository.TranslationRepository;
import com.gagi.swdc.domain.translation.presentation.dto.req.QuestionRequest;
import com.gagi.swdc.global.feign.clova.ClovaChatClient;
import com.gagi.swdc.global.feign.clova.dto.req.ClovaChatCompletionsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final TranslationRepository translationRepository;
    private final MeanRepository meanRepository;
    private final ClovaChatClient clovaChatClient;

    @Transactional
    public String execute(QuestionRequest request) {
        List<String> answer = parsing(makeAnswer(request.getQuestion()));
        List<Mean> means = new ArrayList<>();

        for (int i = 1; i < answer.size(); i++) {
            String[] mean = answer.get(i).split("-");
            means.add(new Mean(mean[0].trim(), mean[1].trim()));
        }

        meanRepository.saveAll(means);
        translationRepository.save(request.toEntity(answer.get(0)));

        return answer.get(0);
    }

    private String makeAnswer(String question) {
        Map<String, Object> resposne = null;
        try {
            resposne = clovaChatClient.clovaChatCompletions(
                    ClovaChatCompletionsRequest.data(question));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> result = (Map<String, Object>) resposne.get("result");
        Map<String, Object> message = (Map<String, Object>) result.get("message");

        return (String) message.get("content");
    }

    private List<String> parsing(String answer) {
        List<String> translate = new ArrayList<>();

        String[] word = answer.split("\n\n");
        translate.add(word[0]);

        String[] means = word[1].split("\n");
        for (String mean: means) {
            translate.add(mean);
        }

        return translate;
    }
}
