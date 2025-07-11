package com.spring.boot.ecommerce.services;

import com.spring.boot.ecommerce.dtos.ProductDTO;
import com.spring.boot.ecommerce.entities.Product;
import com.spring.boot.ecommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = repository.findAll(pageable);
        return products.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();
        copyDtoToEntity(product, dto);
        product = repository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        Product product = repository.getReferenceById(id);
        copyDtoToEntity(product, dto);

        product = repository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    private void copyDtoToEntity(Product product, ProductDTO dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
    }
}
