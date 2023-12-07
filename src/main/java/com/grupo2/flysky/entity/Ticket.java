package com.grupo2.flysky.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;

    @OneToOne
    private Flight flight;

    @OneToOne
    private Client client;

    @Column(nullable = false)
    private Double sellingPrice;
    private Double reservePrice;

    @OneToOne
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private boolean reservation;

}
