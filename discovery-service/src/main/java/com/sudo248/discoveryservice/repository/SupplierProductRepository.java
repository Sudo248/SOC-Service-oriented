package com.sudo248.discoveryservice.repository;

import com.sudo248.discoveryservice.repository.entity.SupplierProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProduct, String> {
}
