package com.grupo2.flysky.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private FlightDto flight;

    private ClientDto client;

    private Double finalPrice;

    private boolean reservation;
}
