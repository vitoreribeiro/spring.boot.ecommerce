package com.spring.boot.ecommerce.repositories;

import com.spring.boot.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
