package com.sudo248.authservice.repository.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "phone_number")
    private String phoneNumber;

    // hash by bcrypt
    private String password;

    private Provider provider;

    @Column(name = "is_validated")
    private boolean isValidated;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return userId != null && Objects.equals(userId, account.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
