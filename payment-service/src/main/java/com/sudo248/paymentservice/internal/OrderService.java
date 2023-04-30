package com.sudo248.paymentservice.internal;


import com.sudo248.domain.base.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "INVOICE-SERVICE")
@Service
public interface OrderService {

    @PatchMapping("/api/v1/invoice//{invoiceId}/payment")
    ResponseEntity<BaseResponse<?>> updateInvoice(@PathVariable("invoiceId") String invoiceId, @RequestBody String paymentId);
}
