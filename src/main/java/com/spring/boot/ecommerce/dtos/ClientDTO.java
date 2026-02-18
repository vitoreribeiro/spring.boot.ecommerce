package com.spring.boot.ecommerce.dtos;

import com.spring.boot.ecommerce.entities.User;

public class ClientDTO {
	
	private Long id;
	private String name;
	
	public ClientDTO() {
	}
	
	public ClientDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ClientDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
