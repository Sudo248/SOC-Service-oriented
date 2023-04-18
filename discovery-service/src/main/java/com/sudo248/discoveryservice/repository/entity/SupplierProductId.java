package com.sudo248.discoveryservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class SupplierProductId implements Serializable {
    @Column(name="product_id")
    private String productId;

    @Column(name="supplier_id")
    private String supplierId;
}
