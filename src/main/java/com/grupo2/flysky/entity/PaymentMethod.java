package com.grupo2.flysky.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaymentMethod;

    @Column(nullable = false)
    private String paymentMode;

    private LocalDateTime paymentDate;

    //establecer fecha automaticamente
    @PrePersist
    public void prePersist() {
        // Establecer la fecha actual solo si paymentDate es null
        if (paymentDate == null) {
            paymentDate = LocalDateTime.now();
        }
    }
}
