package com.sudo248.userservice.repository;

import com.sudo248.userservice.repository.entitity.Address;
import com.sudo248.userservice.repository.entitity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}
