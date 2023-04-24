package com.sudo248.cartservice.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@Getter
@Setter
@Embeddable
public class SupplierProductKey implements Serializable {
    @Column(name="product_id")
    String productId;
    @Column(name="supplier_id")
    String supplierId;
    @Column(name="cart_id")
    String cartId;

    public SupplierProductKey() {
    }

    public SupplierProductKey(String productId, String supplierId, String cartId1) {
        this.productId = productId;
        this.supplierId = supplierId;
        this.cartId = cartId1;
    }

}
