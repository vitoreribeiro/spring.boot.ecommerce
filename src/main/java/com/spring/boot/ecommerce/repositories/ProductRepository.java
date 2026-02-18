package com.spring.boot.ecommerce.repositories;

import com.spring.boot.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query( "SELECT p                                              \n" +
            "  FROM Product p                                      \n" +
            " WHERE UPPER(p.name) LIKE UPPER(CONCAT('%', :name, '%'))" )
    Page<Product> searchByName(String name, Pageable pageable);
}
