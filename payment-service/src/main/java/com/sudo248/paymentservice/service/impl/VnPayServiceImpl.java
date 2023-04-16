package com.sudo248.paymentservice.service.impl;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.paymentservice.config.VnPayConfig;
import com.sudo248.paymentservice.controller.dto.PaymentDto;
import com.sudo248.paymentservice.external.OrderService;
import com.sudo248.paymentservice.repository.PaymentRepository;
import com.sudo248.paymentservice.repository.entity.PaymentStatus;
import com.sudo248.paymentservice.service.PaymentService;
import com.sudo248.paymentservice.service.model.PaymentModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.text.SimpleDateFormat;

@Service
public class VnPayServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    public VnPayServiceImpl(PaymentRepository paymentRepository, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    public ResponseEntity<BaseResponse<?>> pay(PaymentDto paymentDto) {
        return handleException(() -> {
            Double amount = 0.0;
            PaymentModel paymentModel = toModel(paymentDto);
            paymentModel.setAmount(amount);

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", VnPayConfig.vnp_Version);
            vnp_Params.put("vnp_Command", VnPayConfig.vnp_Command);
            vnp_Params.put("vnp_TmnCode", VnPayConfig.vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(VnPayConfig.getVnPayAmount(paymentModel.getAmount())));
            vnp_Params.put("vnp_CurrCode", VnPayConfig.vnp_CurrCode);

            if (paymentModel.getBankCode() != null && !paymentModel.getBankCode().isEmpty()) {
                vnp_Params.put("vnp_BankCode", paymentModel.getBankCode());
            }

            vnp_Params.put("vnp_TxnRef", paymentModel.getPaymentId());
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + paymentModel.getPaymentId());
            vnp_Params.put("vnp_OrderType", paymentModel.getOrderType());
            vnp_Params.put("vnp_Locale", VnPayConfig.vnp_Locale);
            vnp_Params.put("vnp_ReturnUrl", VnPayConfig.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", paymentDto.getIpAddress());

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());

            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();

            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }

            String queryUrl = query.toString();
            String vnp_SecureHash = VnPayConfig.hmacSHA512(VnPayConfig.vnp_HashSecret, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = VnPayConfig.vnp_Url + "?" + queryUrl;
           return BaseResponse.ok(paymentUrl);
        });
    }

    private PaymentModel toModel(PaymentDto paymentDto) {
        return new PaymentModel(
            createId(), paymentDto.getOrderId(), paymentDto.getOrderType(), 0.0, paymentDto.getBankCode(), paymentDto.getPaymentType(), PaymentStatus.INIT
        );
    }
}
