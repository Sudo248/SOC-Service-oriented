package com.sudo248.cartservice.repository.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cartSupplierProduct")
@NoArgsConstructor
@AllArgsConstructor
public class CartSupplierProduct {
    @EmbeddedId
    SupplierProductKey id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "totalPrice")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false,insertable= false, updatable = false, referencedColumnName = "cart_id")
    private Cart cart;

}
