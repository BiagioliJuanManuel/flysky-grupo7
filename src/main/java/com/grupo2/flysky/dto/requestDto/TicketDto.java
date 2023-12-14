package com.grupo2.flysky.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private String name;

    private Long documentNumber;

    private String email;

    private Integer age;

    private String phoneNumber;

    private Long idFlight;

    private Double finalPrice;

    private boolean reservation;
}
