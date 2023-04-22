package com.sudo248.authservice.repository.entity;

public enum Role {
    CONSUMER("consumer"),
    STAFF("staff"),
    ADMIN("admin");

    private final String value;


    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
