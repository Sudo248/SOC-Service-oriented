package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.service.SupplierService;
import com.sudo248.domain.base.BaseResponse;
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
    public ResponseEntity<BaseResponse<?>> addSupplier(@RequestBody SupplierDto supplierDto) {
        return Utils.handleException(() -> {
            SupplierDto savedSupplier = supplierService.addSupplier(supplierDto);
            return BaseResponse.ok(savedSupplier);
        });
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAllSuppliers() {
        return Utils.handleException(() -> {
            List<SupplierDto> suppliers = supplierService.getAllSuppliers();
            return BaseResponse.ok(suppliers);
        });
    }
    @GetMapping("/{supplierName}")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> getSupplierByName(@PathVariable String supplierName) {
        return Utils.handleException(() -> {
            SupplierDto supplier = supplierService.getSupplierByName(supplierName);
            if (supplier == null) {
                BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist supplier");
            }
            return BaseResponse.ok(supplier);
        });
    }
}
