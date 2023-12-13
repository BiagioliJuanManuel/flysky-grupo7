package com.grupo2.flysky.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private Long documentNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "client" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> ticket;
}
