package com.grupo2.flysky.dto.responseDto;

import com.grupo2.flysky.entity.Client;
import com.grupo2.flysky.entity.Flight;
import com.grupo2.flysky.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Flight flight;

    private Client client;

    private Double sellingPrice;
    private Double reservePrice;

    private PaymentMethod paymentMethod;

    private boolean reservation;
}
