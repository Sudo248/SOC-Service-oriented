package com.sudo248.discoveryservice.repository.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "category-product")
@NoArgsConstructor
public class CategoryProduct {

    @EmbeddedId
    private CategoryProductEmbeddable embeddedId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name="category_id")
    private Category category;
}
