package com.sudo248.discoveryservice.repository.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "suppliers")
@NoArgsConstructor
public class Supplier{

    @Id
    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "avatar")
    private String avatar;

    @Transient
    private Location location;

    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SupplierProduct> supplierProducts;
}


