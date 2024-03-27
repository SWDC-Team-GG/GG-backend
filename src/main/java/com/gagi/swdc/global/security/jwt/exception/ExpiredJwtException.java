package com.gagi.swdc.global.security.jwt.exception;

import com.gagi.swdc.global.config.error.exception.BusinessException;
import com.gagi.swdc.global.config.error.exception.ErrorCode;

public class ExpiredJwtException extends BusinessException {

    public static final BusinessException EXCEPTION =
            new ExpiredJwtException();

    private ExpiredJwtException() { super(ErrorCode.EXPIRED_JWT); }
}