package com.sudo248.paymentservice.controller;

import com.sudo248.paymentservice.controller.dto.VnPayResponse;
import com.sudo248.paymentservice.service.VnpayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment/vnpay")
@Slf4j
public class VnPayIPNController {

    private final VnpayService vnpayService;

    public VnPayIPNController(VnpayService vnpayService) {
        this.vnpayService = vnpayService;
    }

    @GetMapping("/ipn-vnpay")
    public VnPayResponse ipnVnpay(
            @RequestParam("vnp_TmnCode") String vnp_TmnCode,
            @RequestParam("vnp_Amount") long vnp_Amount,
            @RequestParam("vnp_BankCode") String vnp_BankCode,
            @RequestParam("vnp_BankTranNo") String vnp_BankTranNo,
            @RequestParam("vnp_CardType") String vnp_CardType,
            @RequestParam("vnp_PayDate") long vnp_PayDate,
            @RequestParam("vnp_OrderInfo") String vnp_OrderInfo,
            @RequestParam("vnp_TransactionNo") long vnp_TransactionNo,
            @RequestParam("vnp_ResponseCode") String vnp_ResponseCode,
            @RequestParam("vnp_TransactionStatus") String vnp_TransactionStatus,
            @RequestParam("vnp_TxnRef") String vnp_TxnRef,
            @RequestParam("vnp_SecureHashType") String vnp_SecureHashType,
            @RequestParam("vnp_SecureHash") String vnp_SecureHash
    ) {
        log.info("Sudoo: " + "vnpay server call ipn");
        return vnpayService.ipnVnpay(
                vnp_TmnCode,
                vnp_Amount,
                vnp_BankCode,
                vnp_BankTranNo,
                vnp_CardType,
                vnp_PayDate,
                vnp_OrderInfo,
                vnp_TransactionNo,
                vnp_ResponseCode,
                vnp_TransactionStatus,
                vnp_TxnRef,
                vnp_SecureHashType,
                vnp_SecureHash
        );
    }
}
