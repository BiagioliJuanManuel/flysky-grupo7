package com.grupo2.flysky.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;

    private Double sellingPrice;
    private Double reservePrice;


    @Column(nullable = false)
    private boolean reservation;

    @OneToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @OneToOne
    @JoinColumn(name = "idPaymentMethod")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "idFlight")
    private Flight flight;


}
