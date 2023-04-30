package com.sudo248.discoveryservice.repository;

import com.sudo248.discoveryservice.repository.entity.Supplier;
import com.sudo248.discoveryservice.repository.entity.SupplierProduct;
import com.sudo248.discoveryservice.repository.entity.SupplierProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProduct, SupplierProductId> {
    List<SupplierProduct> getBySupplier(Supplier supplier);

    List<SupplierProduct> getSupplierProductBySupplier(Supplier supplier);

    List<SupplierProduct> getSupplierProductsBySupplier(Supplier supplier);

    List<SupplierProduct> getAllBySupplier(Supplier supplier);

}
