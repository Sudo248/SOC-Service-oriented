package com.sudo248.authservice.contronller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String addressId = "";

    private int provinceID = 0;

    private int districtID = 0;

    private int wardCode = 0;

    private String provinceName = "";

    private String districtName = "";

    private String wardName = "";

    private String address = "";

    private LocationDto location;

    private String fullAddress = "";
}
