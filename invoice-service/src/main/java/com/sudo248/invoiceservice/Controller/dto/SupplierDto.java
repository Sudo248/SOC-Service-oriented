package com.sudo248.invoiceservice.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto implements Serializable {
    private String supplierId;
    private String  name, avatar;
}