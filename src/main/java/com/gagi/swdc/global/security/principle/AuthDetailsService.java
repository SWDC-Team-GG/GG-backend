package com.gagi.swdc.global.security.principle;

import com.gagi.swdc.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {
    private final UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return new AuthDetails(userFacade.getUserByEmail(email));
    }
}
