package com.sudo248.discoveryservice.repository.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sku")
    private String sku;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<SupplierProduct> supplierProducts;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<CategoryProduct> categoryProducts;


    public Product(int productId, String name, String description, String sku) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.sku = sku;
    }


}
