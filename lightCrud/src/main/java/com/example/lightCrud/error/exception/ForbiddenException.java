package com.example.lightCrud.error.exception;

import com.example.lightCrud.error.ErrorCode;

public class ForbiddenException extends BusinessException{
    public ForbiddenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
