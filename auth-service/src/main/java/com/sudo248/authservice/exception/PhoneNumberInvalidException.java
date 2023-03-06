package com.sudo248.authservice.exception;

import com.sudo248.domain.common.ErrorMessage;
import com.sudo248.domain.exception.ApiException;
import org.springframework.http.HttpStatus;

public class PhoneNumberInvalidException extends ApiException {
    public PhoneNumberInvalidException() {
        super(HttpStatus.BAD_REQUEST, ErrorMessage.PHONE_NUMBER_INVALID);
    }
}
