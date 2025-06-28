package com.spring.boot.ecommerce.controllers;

import com.spring.boot.ecommerce.dtos.ProductDTO;
import com.spring.boot.ecommerce.entities.Product;
import com.spring.boot.ecommerce.repositories.ProductRepository;
import com.spring.boot.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

}
