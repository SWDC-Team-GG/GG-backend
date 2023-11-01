package com.gagi.swdc.domain.user;

import com.gagi.swdc.web.dto.UserInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 주어진 userId와 password에 대응하는 사용자를 조회하는 데 사용됩니다.
    User findByUserIdAndPassword(String userId, String password);

//    주어진 userId에 대응하는 사용자를 조회하는 데 사용됩니다.
    User findByUserId(String userId);
}
