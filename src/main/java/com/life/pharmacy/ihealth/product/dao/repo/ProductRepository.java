package com.life.pharmacy.ihealth.product.dao.repo;

import com.life.pharmacy.ihealth.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findAll();
}
