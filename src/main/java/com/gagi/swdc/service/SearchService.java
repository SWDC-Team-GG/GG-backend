package com.gagi.swdc.service;

import com.gagi.swdc.domain.search.SearchRepository;
import com.gagi.swdc.web.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final SearchRepository searchRepository;

    @Transactional
    public void save(SearchDto searchDto) {
        searchRepository.save(searchDto.toEntity()).getId();
    }
}
