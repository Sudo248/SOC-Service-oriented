package com.sudo248.discoveryservice.repository.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "image")
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "url")
    private String url;

    @Column(name = "ownerId")
    private String ownerId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
    private Product product;
    public Image(int imageId, String url, String ownerId) {
        this.imageId = imageId;
        this.url = url;
        this.ownerId = ownerId;
    }

    public Image(int imageId, String url, String ownerId, Product product) {
        this.imageId = imageId;
        this.url = url;
        this.ownerId = ownerId;
        this.product = product;
    }
}
