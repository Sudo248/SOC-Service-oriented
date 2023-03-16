package com.sudo248.domain.base;

import com.sudo248.domain.exception.ApiException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface HandleException {
    ResponseEntity<BaseResponse<?>> handle() throws ApiException, IOException;
}
