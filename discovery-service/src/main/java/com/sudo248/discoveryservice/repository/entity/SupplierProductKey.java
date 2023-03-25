package com.sudo248.discoveryservice.repository.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class SupplierProductKey implements Serializable {
    @Column(name="product_id")
    int productId;
    @Column(name="supplier_id")
    int supplierId;
}
