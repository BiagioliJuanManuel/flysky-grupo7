package com.grupo2.flysky.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFlight;

    @Column(nullable = false)
    private LocalDateTime flightDate;

    @Column(nullable = false)
    private String airline;

    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Integer availableSeats;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @ManyToOne(targetEntity = Client.class)
    private List<Client> passengers;

}
