package com.sudo248.discoveryservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String addressId;

    private int provinceID = -1;

    private int districtID = -1;

    private int wardCode = -1;

    private String provinceName = "";

    private String districtName = "";

    private String wardName = "";

    private String address = "";

    private double longitude;

    private double latitude;

    private String fullAddress = "";
}
