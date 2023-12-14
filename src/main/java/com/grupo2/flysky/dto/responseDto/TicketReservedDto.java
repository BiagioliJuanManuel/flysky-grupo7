package com.grupo2.flysky.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TicketReservedDto {
    private Long idTicket;

    private String name;

    private Long documentNumber;

    private Double finalPrice;

    private String origin;

    private String destination;
}
