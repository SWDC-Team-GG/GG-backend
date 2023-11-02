package com.gagi.swdc.service;

import com.gagi.swdc.domain.search.SearchRepository;
import com.gagi.swdc.web.dto.SaveSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final SearchRepository searchRepository;

    public boolean save(SaveSearchDto saveSearchDto) {
        try {
            searchRepository.save(saveSearchDto.toEntity());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
