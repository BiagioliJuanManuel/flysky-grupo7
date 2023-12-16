package com.grupo2.flysky.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;

    private Double finalPrice;

    private String paymentMethod;

    private LocalDate paymentDate;
    @Column(nullable = false)
    private boolean reservation;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idFlight")
    private Flight flight;

}
