package com.gagi.swdc.web;

import com.gagi.swdc.service.SearchService;
import com.gagi.swdc.web.dto.SaveSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SearchApiController {
    private final SearchService searchService;

    @PostMapping("/search")
    public void save(SaveSearchDto saveSearchDto) {
        searchService.save(saveSearchDto);
    }
}
