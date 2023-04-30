package com.sudo248.cartservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto implements Serializable {
    private String supplierId;
    private String  name, avatar;
}
