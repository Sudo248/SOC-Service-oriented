package com.sudo248.cartservice.repository;

import com.sudo248.cartservice.repository.entity.CartSupplierProduct;
import com.sudo248.cartservice.repository.entity.SupplierProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartSupplierProductRepository extends JpaRepository<CartSupplierProduct, Long> {
}
