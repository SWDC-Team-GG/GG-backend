package com.gagi.swdc.global.security.jwt.exception;

import com.gagi.swdc.global.config.error.exception.BusinessException;
import com.gagi.swdc.global.config.error.exception.ErrorCode;

public class InvalidJwtException extends BusinessException {

    public static final BusinessException EXCEPTION =
            new InvalidJwtException();

    private InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }
}