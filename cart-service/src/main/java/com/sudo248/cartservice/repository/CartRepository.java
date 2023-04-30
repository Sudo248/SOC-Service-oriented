package com.sudo248.cartservice.repository;

import com.sudo248.cartservice.repository.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

    Cart findByUserIdAndStatus(String userId, String status);

}
