package com.sudo248.discoveryservice.controller.dto;

import java.util.List;

public class SupplierDto {
    private int supplierId;
    private String  name, avatar, location;
    private List<SupplierProductDto> supplierProducts;
    public SupplierDto() {
    }

    public SupplierDto(int supplierId, String name, String avatar) {
        this.supplierId = supplierId;
        this.name = name;
        this.avatar = avatar;
    }

    public SupplierDto(int supplierId, String name, String avatar, String location) {
        this.supplierId = supplierId;
        this.name = name;
        this.avatar = avatar;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
