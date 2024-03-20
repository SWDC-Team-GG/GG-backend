package com.gagi.swdc.domain.auth.exception;

import com.gagi.swdc.global.config.error.exception.BusinessException;
import com.gagi.swdc.global.config.error.exception.ErrorCode;

public class UnexpectedTokenException extends BusinessException {
    public static final BusinessException EXCEPTION =
            new UnexpectedTokenException();

    private UnexpectedTokenException() {super(ErrorCode.INVALID_JWT);}
}
