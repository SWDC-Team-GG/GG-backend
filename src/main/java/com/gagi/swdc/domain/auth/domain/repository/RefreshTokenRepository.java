package com.gagi.swdc.domain.auth.domain.repository;

import com.gagi.swdc.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}