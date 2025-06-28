package com.spring.boot.ecommerce.services;

import com.spring.boot.ecommerce.dtos.ProductDTO;
import com.spring.boot.ecommerce.entities.Product;
import com.spring.boot.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }
}
