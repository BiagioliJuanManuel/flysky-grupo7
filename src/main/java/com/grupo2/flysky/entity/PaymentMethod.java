package com.grupo2.flysky.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class PaymentMethod {
    private String paymentMode;
    private LocalDateTime paymentDate;
}
