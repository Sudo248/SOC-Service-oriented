package com.sudo248.discoveryservice.repository.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "category")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "supplierId")
    private String supplierId;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<CategoryProduct> categoryProducts;

    public Category(int categoryId, String name, String image, String supplierId) {
        this.categoryId = categoryId;
        this.name = name;
        this.image = image;
        this.supplierId = supplierId;
    }


}

