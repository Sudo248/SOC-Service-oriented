package com.sudo248.cartservice.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cart")
@NoArgsConstructor
public class Cart {
    @Id
    @Column(name = "cart_id")
    private String cartId;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name = "totalAmount")
    private int totalAmount;

    @Column(name = "userId")
    private String userId;

    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartSupplierProduct> cartSupplierProducts;
}
