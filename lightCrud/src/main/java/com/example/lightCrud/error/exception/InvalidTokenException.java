package com.example.lightCrud.error.exception;

import com.example.lightCrud.error.ErrorCode;

public class InvalidTokenException extends BusinessException{
    public InvalidTokenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
