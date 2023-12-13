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
    private FlightDto flight;

    private ClientDto client;

    private Double sellingPrice;
    private Double reservePrice;

    private PaymentMethodDto paymentMethod;

    private boolean reservation;
}
