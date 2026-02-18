package com.spring.boot.ecommerce.dtos;

import com.spring.boot.ecommerce.entities.User;
import lombok.Getter;

@Getter
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

}
