package com.spring.boot.ecommerce.dtos;

import com.spring.boot.ecommerce.entities.Product;
import jakarta.validation.constraints.*;

public class ProductDTO {

    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres.")
    @NotBlank(message = "Campo requerido.")
    private String name;
    @Size(min = 10, message = "A descrição deve ter ao menos 10 caracteres.")
    @NotBlank(message = "Campo requerido.")
    private String description;
    @Positive(message = "O preço deve ser positivo.")
    private Double price;
    private String imgUrl;

    public ProductDTO() {

    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
