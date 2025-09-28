package com.ecom.testproducts.repository;

import com.ecom.testproducts.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);

    Product findByName(String string);
}
