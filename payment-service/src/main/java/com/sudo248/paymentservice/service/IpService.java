package com.sudo248.paymentservice.service;

import javax.servlet.http.HttpServletRequest;

public interface IpService {
    String getIpAddress(HttpServletRequest request);
}
