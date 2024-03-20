package com.gagi.swdc.domain.translation.presentation;

import com.gagi.swdc.domain.translation.presentation.dto.req.QuestionRequest;
import com.gagi.swdc.domain.translation.presentation.dto.res.AnswerResponse;
import com.gagi.swdc.domain.translation.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/translate")
@RestController
public class TranslationController {
    private final QuestionService questionService;

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping
    public AnswerResponse question(@RequestBody QuestionRequest request) {
        return questionService.execute(request);
    }
}
