package com.example.lightCrud.Jwt;

import javax.naming.AuthenticationException;

public class JwtExpiredException extends AuthenticationException {
    public JwtExpiredException(String message) {
        super(message);
    }
}
