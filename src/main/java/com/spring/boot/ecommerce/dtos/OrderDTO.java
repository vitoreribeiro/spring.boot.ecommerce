package com.spring.boot.ecommerce.dtos;

import com.spring.boot.ecommerce.entities.Order;
import com.spring.boot.ecommerce.entities.OrderItem;
import com.spring.boot.ecommerce.enums.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;

    private ClientDTO client;
    private PaymentDTO payment;
    @NotEmpty(message = "Deve ter ao menos 1 item.")
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.status = entity.getStatus();
        this.client = new ClientDTO(entity.getClient());
        this.payment = entity.getPayment()== null ? null : new PaymentDTO(entity.getPayment());
        for (OrderItem item  : entity.getOrderItems()) {
            OrderItemDTO itemDTO = new OrderItemDTO(item);
            items.add(itemDTO);
        }
    }

    public Double getTotal() {
        double sum = 0.0;
        for(OrderItemDTO item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }

}
