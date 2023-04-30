package com.sudo248.discoveryservice.repository;

import com.sudo248.discoveryservice.repository.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    @Query(value = "SELECT * FROM suppliers WHERE user_id=:userId LIMIT 1", nativeQuery = true)
    Supplier getRawSupplierByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM suppliers WHERE supplier_id=:supplierId LIMIT 1", nativeQuery = true)
    Supplier getRawSupplierById(@Param("supplierId") String supplierId);

    Supplier getSupplierByUserId(String userId);
}