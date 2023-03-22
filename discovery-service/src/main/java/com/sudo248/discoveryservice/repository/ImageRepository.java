package com.sudo248.discoveryservice.repository;

import com.sudo248.discoveryservice.repository.entity.Image;
import com.sudo248.discoveryservice.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {


}
