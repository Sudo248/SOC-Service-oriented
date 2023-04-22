package com.sudo248.userservice.repository;

import com.sudo248.userservice.repository.entitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
