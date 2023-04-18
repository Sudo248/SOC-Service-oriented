package com.sudo248.paymentservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment/vnpay")
public class VnPayReturnController {

    @GetMapping("/return-vnpay")
    public String returnVnPay(

    ) {
        return "Return_VnPay";
    }

}
