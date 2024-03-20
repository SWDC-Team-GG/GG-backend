package com.gagi.swdc.domain.translation.domain.repository;

import com.gagi.swdc.domain.translation.domain.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<Translation, Long> {
}
