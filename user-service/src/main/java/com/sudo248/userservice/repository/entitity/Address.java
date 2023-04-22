package com.sudo248.userservice.repository.entitity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "province_id")
    private int provinceID;

    @Column(name = "district_id")
    private int districtID;

    @Column(name = "ward_code")
    private int wardCode;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "address")
    private String address;

    @Embedded
    private Location location;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private String fullAddress;

    public Address(String userId, int provinceID, int districtID, int wardCode, String provinceName, String districtName, String wardName, String address, Location location, User user) {
        this.userId = userId;
        this.provinceID = provinceID;
        this.districtID = districtID;
        this.wardCode = wardCode;
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.wardName = wardName;
        this.address = address;
        this.location = location;
        this.user = user;
    }

    public Address(String userId, int provinceID, int districtID, int wardCode, String provinceName, String districtName, String wardName, String address, Location location) {
        this.userId = userId;
        this.provinceID = provinceID;
        this.districtID = districtID;
        this.wardCode = wardCode;
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.wardName = wardName;
        this.address = address;
        this.location = location;
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    private void setFullAddress() {
        fullAddress = address + ", " + wardName + ", " + districtName + ", " + provinceName;
    }

    public String getAddressId() {
        return userId;
    }
}
