package com.spring.boot.ecommerce.services;

import com.spring.boot.ecommerce.dtos.OrderDTO;
import com.spring.boot.ecommerce.dtos.OrderItemDTO;
import com.spring.boot.ecommerce.entities.Order;
import com.spring.boot.ecommerce.entities.OrderItem;
import com.spring.boot.ecommerce.entities.Product;
import com.spring.boot.ecommerce.entities.User;
import com.spring.boot.ecommerce.enums.OrderStatus;
import com.spring.boot.ecommerce.repositories.OrderItemRepository;
import com.spring.boot.ecommerce.repositories.OrderRepository;
import com.spring.boot.ecommerce.repositories.ProductRepository;
import com.spring.boot.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido não encontrado."));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }
    @Transactional
    public OrderDTO insert (OrderDTO dto){

        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        User user = userService.authenticated();
        order.setClient(user);

        for(OrderItemDTO itemDTO : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem orderItem = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getOrderItems().add(orderItem);
        }

        repository.save(order);
        orderItemRepository.saveAll(order.getOrderItems());

        return new OrderDTO(order);
    }

}
