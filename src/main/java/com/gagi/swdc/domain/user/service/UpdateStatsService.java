package com.gagi.swdc.domain.user.service;

import com.gagi.swdc.domain.user.domain.User;
import com.gagi.swdc.domain.user.facade.UserFacade;
import com.gagi.swdc.domain.user.presentation.dto.req.StatsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateStatsService {
    private final UserFacade userFacade;

    public void execute(StatsRequest request) {
        User user = userFacade.getCurrentUser();
        user.updateStats(request.getStats());
    }
}
