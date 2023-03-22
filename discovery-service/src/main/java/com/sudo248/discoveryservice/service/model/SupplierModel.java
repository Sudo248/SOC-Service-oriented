package com.sudo248.discoveryservice.service.model;

public class SupplierModel {
    private String supplierId, name, avatar;

    public SupplierModel() {
    }

    public SupplierModel(String supplierId, String name, String avatar) {
        this.supplierId = supplierId;
        this.name = name;
        this.avatar = avatar;
    }

}
