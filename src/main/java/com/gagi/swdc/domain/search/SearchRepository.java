package com.gagi.swdc.domain.search;

import com.gagi.swdc.web.dto.SearchDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchRepository extends JpaRepository<Search, Long> {
    List<Search> findByUserId(Long userId);
}
