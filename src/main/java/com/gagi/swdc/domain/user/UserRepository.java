package com.gagi.swdc.domain.user;

import com.gagi.swdc.web.dto.UserInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserIdAndPassword(String userId, String password);
}
