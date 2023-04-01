package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.service.SupplierService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/supplier")
    public ResponseEntity<BaseResponse<?>> addSupplier(@RequestBody SupplierDto supplierDto) {
        return Utils.handleException(() -> {
            SupplierDto savedSupplier = supplierService.addSupplier(supplierDto);
            return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", savedSupplier));
        });

    }

    @GetMapping("/supplier")
    public ResponseEntity<BaseResponse<?>> getAllSuppliers() {
        return Utils.handleException(() -> {
            List<SupplierDto> suppliers = supplierService.getAllSuppliers();
            return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", suppliers));
        });
    }
    @GetMapping("/supplier/{name}")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> getSupplierByName(@PathVariable String name) {
        return Utils.handleException(() -> {
            SupplierDto supplier = supplierService.getSupplierByName(name);
            if (supplier == null) {
                BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist supplier");
            }
            return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", supplier));
        });
    }
}
