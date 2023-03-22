package com.sudo248.discoveryservice.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "supplierProduct")
@NoArgsConstructor
public class SupplierProduct {
    @EmbeddedId
    SupplierProductKey id;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @Column(name="amountLeft")
    private int amountLeft;

    @Column(name="price")
    private double price;

    @Column(name = "soldAmount")
    private double soldAmount;
    @Column(name = "rate")
    private double rate;
}
