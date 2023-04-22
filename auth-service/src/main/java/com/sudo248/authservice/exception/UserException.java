package com.sudo248.authservice.exception;

import com.sudo248.domain.exception.ApiException;
import org.springframework.http.HttpStatus;

public class UserException extends ApiException {
    public UserException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Can't create user");
    }
}
