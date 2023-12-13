package com.grupo2.flysky.service;

import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.dto.requestDto.ReservationDto;
import com.grupo2.flysky.dto.responseDto.ClientDto;
import com.grupo2.flysky.dto.responseDto.FlightDto;
import com.grupo2.flysky.dto.responseDto.ResponseDto;
import com.grupo2.flysky.dto.responseDto.TicketDto;

import java.util.List;

public interface IFlySkyService {

    /*
     *  ResponseDto no seria el objeto final de retorno.
     *
     */

    List<FlightDto> findAllFlights(); // Buscar vuelos

    ResponseDto buyTicket(Long id, ClientRequestDto client); // Comprar o Reservar un Boleto con el numero de Vuelo

    ResponseDto payment(Long idTicket); // Pagar la Compra o Reserva del Boleto

    List<TicketDto> findClient(Long idClient); // Buscar un cliente, para ver sus Datos e Historial
}
