package com.gagi.swdc.web;

import com.gagi.swdc.service.SearchService;
import com.gagi.swdc.web.dto.SaveSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SearchApiController {
    private final SearchService searchService;

    @PostMapping("/search")
    public ResponseEntity<String> save(SaveSearchDto saveSearchDto) {
        if (searchService.save(saveSearchDto)) return ResponseEntity.ok("성공");
        else return ResponseEntity.badRequest().build();
    }
}
