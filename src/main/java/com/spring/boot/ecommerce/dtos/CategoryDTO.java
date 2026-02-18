package com.spring.boot.ecommerce.dtos;

import com.spring.boot.ecommerce.entities.Category;
import lombok.Getter;

@Getter
public class CategoryDTO {

    private Long id;
    private String name;

    public CategoryDTO(){

    }

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
    }

}
