package com.gagi.swdc.domain.user.exception;

import com.gagi.swdc.global.config.error.exception.BusinessException;
import com.gagi.swdc.global.config.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {super(ErrorCode.USER_NOT_FOUND);}
}
