package com.gagi.swdc.domain.search;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Search, Long> {
    Search findByUserId(String userId);
}
