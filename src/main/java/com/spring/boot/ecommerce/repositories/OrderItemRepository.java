package com.spring.boot.ecommerce.repositories;

import com.spring.boot.ecommerce.entities.OrderItem;
import com.spring.boot.ecommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
