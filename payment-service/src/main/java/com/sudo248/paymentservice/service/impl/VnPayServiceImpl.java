package com.sudo248.paymentservice.service.impl;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.paymentservice.config.VnPayConfig;
import com.sudo248.paymentservice.controller.dto.PaymentDto;
import com.sudo248.paymentservice.controller.dto.VnPayResponse;
import com.sudo248.paymentservice.external.OrderService;
import com.sudo248.paymentservice.repository.PaymentRepository;
import com.sudo248.paymentservice.repository.entity.Payment;
import com.sudo248.paymentservice.repository.entity.PaymentStatus;
import com.sudo248.paymentservice.service.PaymentService;
import com.sudo248.paymentservice.service.VnpayService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VnPayServiceImpl implements PaymentService, VnpayService {
    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    public VnPayServiceImpl(PaymentRepository paymentRepository, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    public ResponseEntity<BaseResponse<?>> pay(PaymentDto paymentDto) {
        return handleException(() -> {
            Payment payment = toEntity(paymentDto);

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", VnPayConfig.vnp_Version);
            vnp_Params.put("vnp_Command", VnPayConfig.vnp_Command);
            vnp_Params.put("vnp_TmnCode", VnPayConfig.vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(VnPayConfig.getVnPayAmount(payment.getAmount())));
            vnp_Params.put("vnp_CurrCode", VnPayConfig.vnp_CurrCode);

            if (payment.getBankCode() != null && !payment.getBankCode().isEmpty()) {
                vnp_Params.put("vnp_BankCode", payment.getBankCode());
            }

            vnp_Params.put("vnp_TxnRef", payment.getPaymentId());
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + payment.getPaymentId());
            vnp_Params.put("vnp_OrderType", payment.getOrderType());
            vnp_Params.put("vnp_Locale", VnPayConfig.vnp_Locale);
            vnp_Params.put("vnp_ReturnUrl", VnPayConfig.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", paymentDto.getIpAddress());

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7" ));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss" );
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());

            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator<String> itr = fieldNames.iterator();

            while (itr.hasNext()) {
                String fieldName = itr.next();
                String fieldValue = vnp_Params.get(fieldName);
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
            paymentRepository.save(payment);
            return BaseResponse.ok(paymentUrl);
        });
    }

    private Payment toEntity(PaymentDto paymentDto) {
        return new Payment(
                createId(),
                paymentDto.getOrderId(),
                paymentDto.getOrderType(),
                paymentDto.getAmount(),
                paymentDto.getBankCode(),
                paymentDto.getPaymentType(),
                PaymentStatus.PENDING
        );
    }

    @Override
    public String returnVnPay(
            String vnp_TmnCode,
            long vnp_Amount,
            String vnp_BankCode,
            String vnp_BankTranNo,
            String vnp_CardType,
            long vnp_PayDate,
            String vnp_OrderInfo,
            long vnp_TransactionNo,
            String vnp_ResponseCode,
            String vnp_TransactionStatus,
            String vnp_TxnRef,
            String vnp_SecureHashType,
            String vnp_SecureHash
    ) {
        Map<String, String> fields = new HashMap<>();
        fields.put("vnp_TmnCode", vnp_TmnCode);
        fields.put("vnp_Amount", String.valueOf(vnp_Amount));
        fields.put("vnp_BankCode", vnp_BankCode);
        fields.put("vnp_BankTranNo", vnp_BankTranNo);
        fields.put("vnp_CardType", vnp_CardType);
        fields.put("vnp_PayDate", String.valueOf(vnp_PayDate));
        fields.put("vnp_OrderInfo", vnp_OrderInfo);
        fields.put("vnp_TransactionNo", String.valueOf(vnp_TransactionNo));
        fields.put("vnp_ResponseCode", vnp_ResponseCode);
        fields.put("vnp_TransactionStatus", vnp_TransactionStatus);
        fields.put("vnp_TxnRef", vnp_TxnRef);

        String signValue = VnPayConfig.hashAllFields(fields);

        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(vnp_TransactionStatus)) {
                return "payment_successful";
            } else {
                return "payment_fail";
            }
        } else {
            return "payment_fail";
        }
    }

    @Override
    public VnPayResponse ipnVnpay(
            String vnp_TmnCode,
            long vnp_Amount,
            String vnp_BankCode,
            String vnp_BankTranNo,
            String vnp_CardType,
            long vnp_PayDate,
            String vnp_OrderInfo,
            long vnp_TransactionNo,
            String vnp_ResponseCode,
            String vnp_TransactionStatus,
            String vnp_TxnRef,
            String vnp_SecureHashType,
            String vnp_SecureHash
    ) {
        Map<String, String> fields = new HashMap<>();
        fields.put("vnp_TmnCode", vnp_TmnCode);
        fields.put("vnp_Amount", String.valueOf(vnp_Amount));
        fields.put("vnp_BankCode", vnp_BankCode);
        fields.put("vnp_BankTranNo", vnp_BankTranNo);
        fields.put("vnp_CardType", vnp_CardType);
        fields.put("vnp_PayDate", String.valueOf(vnp_PayDate));
        fields.put("vnp_OrderInfo", vnp_OrderInfo);
        fields.put("vnp_TransactionNo", String.valueOf(vnp_TransactionNo));
        fields.put("vnp_ResponseCode", vnp_ResponseCode);
        fields.put("vnp_TransactionStatus", vnp_TransactionStatus);
        fields.put("vnp_TxnRef", vnp_TxnRef);

        String signValue = VnPayConfig.hashAllFields(fields);

        if (signValue.equals(vnp_SecureHash)) {
            return checkAndUpdatePayment(
                    vnp_TxnRef,
                    vnp_Amount,
                    vnp_TransactionStatus,
                    vnp_ResponseCode
            );
        } else {
            return new VnPayResponse(
                    "99",
                    "Unknown error"
            );
        }
    }

    private VnPayResponse checkAndUpdatePayment(
            String paymentId,
            long amount,
            String paymentStatus,
            String responseCode
    ) {

        if (paymentRepository.existsById(paymentId)) {
            Payment payment = paymentRepository.getReferenceById(paymentId);

            if ((long) (payment.getAmount() * 100) == amount) {

                if ("00".equals(paymentStatus) || payment.getStatus() == PaymentStatus.PENDING) {

                    if ("00".equals(responseCode)) {
                        payment.setStatus(PaymentStatus.SUCCESS);
                    } else {
                        payment.setStatus(PaymentStatus.FAILURE);
                    }
                    paymentRepository.save(payment);
                    return new VnPayResponse(
                            "00",
                            "Confirm Success"
                    );

                } else {
                    return new VnPayResponse(
                            "02",
                            "Payment already confirmed"
                    );
                }

            } else {
                return new VnPayResponse(
                        "04",
                        "Invalid Amount"
                );
            }

        } else {
            return new VnPayResponse(
                    "01",
                    "Payment not Found"
            );
        }
    }
}
