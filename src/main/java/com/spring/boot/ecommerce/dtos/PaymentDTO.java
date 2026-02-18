package com.spring.boot.ecommerce.dtos;

import com.spring.boot.ecommerce.entities.Payment;
import lombok.Getter;

import java.time.Instant;

@Getter
public class PaymentDTO {

    private Long id;
    private Instant moment;


    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }
    
    public PaymentDTO(Payment entity) {
       id = entity.getId();
       moment = entity.getMoment();
    }

}
