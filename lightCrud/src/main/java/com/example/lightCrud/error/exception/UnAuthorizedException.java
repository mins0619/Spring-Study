package com.example.lightCrud.error.exception;

import com.example.lightCrud.error.ErrorCode;

public class UnAuthorizedException extends BusinessException {
    public UnAuthorizedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
