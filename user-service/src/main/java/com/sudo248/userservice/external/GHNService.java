package com.sudo248.userservice.external;

import com.sudo248.domain.common.Constants;
import com.sudo248.userservice.controller.dto.ghn.address.GHNDistrictDto;
import com.sudo248.userservice.controller.dto.ghn.address.GHNProvinceDto;
import com.sudo248.userservice.controller.dto.ghn.address.GHNResponse;
import com.sudo248.userservice.controller.dto.ghn.address.GHNWardDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "GHN", url = "https://dev-online-gateway.ghn.vn/shiip/public-api")
@Service
public interface GHNService {
//    https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province
//    https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=201
//    https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=1542
    @GetMapping(value = "/master-data/province", headers = "token="+ Constants.GHN_TOKEN)
    GHNResponse<List<GHNProvinceDto>> getGHNProvince();

    @GetMapping(value = "/master-data/district", headers = "token="+ Constants.GHN_TOKEN)
    GHNResponse<List<GHNDistrictDto>> getGHNDistrict(@RequestParam("province_id") int provinceId);

    @GetMapping(value = "/master-data/ward", headers = "token="+ Constants.GHN_TOKEN)
    GHNResponse<List<GHNWardDto>> getGHNWard(@RequestParam("district_id") int districtId);
}
