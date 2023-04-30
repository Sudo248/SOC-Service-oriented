package com.sudo248.discoveryservice.repository.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CategoryProductKey implements Serializable {
    @Column(name = "product_id")
    String productId;
    @Column(name = "category_id")
    String categoryId;
}
