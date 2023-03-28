package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.service.SupplierService;
import com.sudo248.domain.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("/api/v1/discovery")
@RestController
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/supplier")
    public ResponseEntity<BaseResponse<?>> addSupplier(@RequestBody SupplierDto supplierDto) {
        SupplierDto savedSupplier = supplierService.addSupplier(supplierDto);
        return BaseResponse.ok(savedSupplier);
    }

    @GetMapping("/supplier")
    public ResponseEntity<BaseResponse<?>> getAllSuppliers() {
        List<SupplierDto> suppliers = supplierService.getAllSuppliers();
        return BaseResponse.ok(suppliers);
    }
    @GetMapping("/supplier/{name}")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> getSupplierByName(@PathVariable String name) {
        SupplierDto supplier = supplierService.getSupplierByName(name);
        return BaseResponse.ok(supplier);
    }
}
