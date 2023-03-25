package com.sudo248.discoveryservice.repository.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "supplier")
@NoArgsConstructor
public class Supplier{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int supplierId;


    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "avatar")
    private String avatar;
    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL)
    private List<SupplierProduct> supplierProducts;
    public Supplier(int supplierId, String name, String avatar) {
        this.supplierId = supplierId;
        this.name = name;
        this.avatar = avatar;
    }
}


