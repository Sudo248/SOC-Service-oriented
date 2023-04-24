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
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;



    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<CategoryProduct> categoryProducts;

    public Category(String categoryId, String name, String image) {
        this.categoryId = categoryId;
        this.name = name;
        this.image = image;
    }


}

