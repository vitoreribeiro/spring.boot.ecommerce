package com.spring.boot.ecommerce.repositories;

import com.spring.boot.ecommerce.entities.OrderItem;
import com.spring.boot.ecommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
