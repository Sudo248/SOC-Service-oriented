package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.service.SupplierService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import com.sudo248.domain.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<?>> addSupplier(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestBody SupplierDto supplierDto
    ) {
        return Utils.handleException(() -> {
            SupplierDto savedSupplier = supplierService.addSupplier(userId, supplierDto);
            return BaseResponse.ok(savedSupplier);
        });
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAllSuppliers(
            @RequestHeader(Constants.HEADER_USER_ID) String userId
    ) {
        return Utils.handleException(() -> {
            List<SupplierDto> suppliers = supplierService.getAllSuppliers(userId);
            return BaseResponse.ok(suppliers);
        });
    }
    @GetMapping("/{supplierName}")
    public ResponseEntity<BaseResponse<?>> getSupplierByName(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("supplierName") String supplierName
    ) {
        return Utils.handleException(() -> {
            SupplierDto supplier = supplierService.getSupplierByName(userId, supplierName);
            if (supplier == null) {
                BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist supplier");
            }
            return BaseResponse.ok(supplier);
        });
    }

    @GetMapping("/{supplierId}/address")
    public ResponseEntity<BaseResponse<?>> getSupplierAddressById(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("supplierId") String supplierId
    ) {
        return Utils.handleException(() -> {
            return supplierService.getSupplierAddress(supplierId);
        });
    }

    @GetMapping("/user")
    public ResponseEntity<BaseResponse<?>> getSupplierByUserId(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestParam(value = "isDetail", required = false, defaultValue = "false") boolean isDetail
    ) {
        return Utils.handleException(() -> {
            SupplierDto supplier = supplierService.getSupplierByUserId(userId, isDetail);
            if (supplier == null) {
                BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist supplier");
            }
            return BaseResponse.ok(supplier);
        });
    }
}
