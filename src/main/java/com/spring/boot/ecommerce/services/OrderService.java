package com.spring.boot.ecommerce.services;

import com.spring.boot.ecommerce.dtos.OrderDTO;
import com.spring.boot.ecommerce.entities.Order;
import com.spring.boot.ecommerce.repositories.OrderRepository;
import com.spring.boot.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido não encontrado."));
        return new OrderDTO(order);
    }
}
