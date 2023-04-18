package com.sudo248.paymentservice.external;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@FeignClient(name = "ORDER-SERVICE", url = "http://order-service:8082/api/v1/order")
@Service
public interface OrderService {

}
