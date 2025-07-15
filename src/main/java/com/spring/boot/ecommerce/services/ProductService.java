package com.spring.boot.ecommerce.services;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.spring.boot.ecommerce.dtos.ProductDTO;
import com.spring.boot.ecommerce.entities.Product;
import com.spring.boot.ecommerce.repositories.ProductRepository;
import com.spring.boot.ecommerce.services.exceptions.DatabaseException;
import com.spring.boot.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));
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
        try{
            Product product = repository.getReferenceById(id);
            copyDtoToEntity(product, dto);

            product = repository.save(product);
            return new ProductDTO(product);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Produto não encontrado.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Produto não encontrado.");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial.");
        }
    }

    private void copyDtoToEntity(Product product, ProductDTO dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
    }
}
