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
@Table(name = "category_product")
@NoArgsConstructor
public class CategoryProduct {
    @EmbeddedId
    private CategoryProductKey id;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name="category_id")
    private Category category;
}
