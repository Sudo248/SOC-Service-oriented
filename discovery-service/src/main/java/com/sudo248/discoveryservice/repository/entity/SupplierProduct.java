package com.sudo248.discoveryservice.repository.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "supplierProduct")
@NoArgsConstructor
public class SupplierProduct {
    @EmbeddedId
    SupplierProductEmbeddable embeddedId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @Column(name="amount_left")
    private int amountLeft;

    @Column(name="price")
    private double price;

    @Column(name = "sold_amount")
    private double soldAmount;

    @Column(name = "rate")
    private double rate;
}
