package com.sudo248.authservice.exception;

import com.sudo248.domain.exception.ApiException;
import org.springframework.http.HttpStatus;

public class PhoneNumberExistedException extends ApiException {

    public PhoneNumberExistedException() {
        super(HttpStatus.BAD_REQUEST, "Phone number existed");
    }
}
