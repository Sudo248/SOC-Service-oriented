package com.sudo248.userservice.service;

import com.sudo248.userservice.controller.dto.AddressSuggestionDto;
import com.sudo248.userservice.external.GHNService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressSuggestionService {

    List<AddressSuggestionDto> getProvinces() throws Exception;

    List<AddressSuggestionDto> getDistricts(int provinceId) throws Exception;

    List<AddressSuggestionDto> getWards(int districtId) throws Exception;

}
