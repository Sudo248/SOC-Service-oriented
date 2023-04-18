package com.sudo248.discoveryservice.repository.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "supplier_product")
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProduct {
    @EmbeddedId
    SupplierProductId supplierProductId;

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

    public SupplierProduct(Product product, Supplier supplier, int amountLeft, double price, double soldAmount, double rate) {
        this.product = product;
        this.supplier = supplier;
        this.amountLeft = amountLeft;
        this.price = price;
        this.soldAmount = soldAmount;
        this.rate = rate;
    }
}
