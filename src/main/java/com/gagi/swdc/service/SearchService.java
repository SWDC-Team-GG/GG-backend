package com.gagi.swdc.service;

import com.gagi.swdc.domain.search.SearchRepository;
import com.gagi.swdc.web.dto.SaveSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final SearchRepository searchRepository;

    public void save(SaveSearchDto saveSearchDto) {
        searchRepository.save(saveSearchDto.toEntity());
    }
}
