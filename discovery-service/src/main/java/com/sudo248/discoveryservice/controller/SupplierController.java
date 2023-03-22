package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.service.SupplierService;
import com.sudo248.domain.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/discovery")
@RestController
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/supplier")
    public ResponseEntity<BaseResponse<?>> addSupplier(@RequestBody SupplierDto supplierDto) {
        SupplierDto savedSupplier = supplierService.addSupplier(supplierDto);
        return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", savedSupplier));
    }

    @GetMapping("/supplier")
    public ResponseEntity<BaseResponse<?>> getAllSuppliers() {
        List<SupplierDto> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", suppliers));
    }
    @GetMapping("/supplier/{name}")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> getSupplierByName(@PathVariable String name) {
        SupplierDto supplier = supplierService.getSupplierByName(name);
        return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", supplier));
    }
}
