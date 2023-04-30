package com.sudo248.domain.util;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.base.HandleException;
import com.sudo248.domain.exception.ApiException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Random;
import java.util.UUID;

public class Utils {
    public static ResponseEntity<BaseResponse<?>> handleException(HandleException handle) {
        try {
            return handle.handle();
        } catch (Exception e) {
            LoggerFactory.getLogger("Utils.HandleException").error(e.getMessage(), e);
            if (e instanceof ApiException) {
                return ((ApiException)e).getResponseEntity();
            }
//            return ResponseEntity.internalServerError().body(e.getMessage());
            return BaseResponse.status(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public static String createIdOrElse(String id) {
        return (id != null && !id.isEmpty()) ? id : createId();
    }

    public static String createId() {
        return UUID.randomUUID() + "-" + System.currentTimeMillis();
    }

    public static String createId(String key) {
        return UUID.fromString(key) + "-" + System.currentTimeMillis();
    }

    public static String genSkuOrElse(String sku) {
        return (sku != null && !sku.isEmpty()) ? sku : String.valueOf(new Random(System.currentTimeMillis()).nextInt(80000) + 10000);
    }
}
