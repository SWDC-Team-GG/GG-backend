package com.gagi.swdc.service;

import com.gagi.swdc.domain.search.SearchRepository;
import com.gagi.swdc.web.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final SearchRepository searchRepository;

    @Transactional
    public void save(SearchDto searchDto) {
        searchRepository.save(searchDto.toEntity()).getId();
    }

    @Transactional
    public List<SearchDto> select(Long userId) {
        return searchRepository.findByUserId(userId).stream()
                .map(SearchDto::new)
                .collect(Collectors.toList());
    }
}
