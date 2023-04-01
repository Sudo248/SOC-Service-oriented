package com.sudo248.domain.base;

import com.sudo248.domain.exception.ApiException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface BaseService {
    default ResponseEntity<BaseResponse<?>> handleException(HandleException handle) {
        try {
            return handle.handle();
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass().getName()).error(e.getMessage(), e);
            if (e instanceof ApiException) {
                return ((ApiException)e).getResponseEntity();
            }
//            return ResponseEntity.internalServerError().body(e.getMessage());
            return BaseResponse.status(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    default String createId() {
        return UUID.randomUUID() + "-" + System.currentTimeMillis();
    }

    default String createId(String key) {
        return UUID.fromString(key) + "-" + System.currentTimeMillis();
    }
}
