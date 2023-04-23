package com.sudo248.paymentservice.controller;

import com.sudo248.paymentservice.controller.dto.VnPayResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment/vnpay")
public class VnPayIPNController {
    @GetMapping("/ipn-vnpay")
    public VnPayResponse ipnVnpay() {
        System.out.println("/api/v1/payment/vnpay/ipn-vnpay");
        return new VnPayResponse();
    }
}
