package com.gagi.swdc.domain.user.presentation;

import com.gagi.swdc.domain.user.presentation.dto.req.StatsRequest;
import com.gagi.swdc.domain.user.presentation.dto.res.UserInfoResponse;
import com.gagi.swdc.domain.user.service.LoginUserInfoService;
import com.gagi.swdc.domain.user.service.UpdateStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final LoginUserInfoService loginUserInfoService;
    private final UpdateStatsService updateStatsService;

    @GetMapping
    public UserInfoResponse profile() {
        return loginUserInfoService.execute();
    }

    @PostMapping
    public void stats(@RequestBody StatsRequest request) {
        updateStatsService.execute(request);
    }
}
