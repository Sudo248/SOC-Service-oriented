package com.sudo248.userservice.service;

import com.sudo248.userservice.controller.dto.AddressDto;
import com.sudo248.userservice.repository.entitity.Address;
import com.sudo248.userservice.repository.entitity.Location;

public interface AddressService {
    AddressDto getAddress(String userId);
    void deleteAddress(String userId);
    AddressDto putAddress(String userId, AddressDto addressDto);

    Location getLocation(String userId);

    AddressDto toDto(Address address);
    Address toEntity(AddressDto addressDto);
}
