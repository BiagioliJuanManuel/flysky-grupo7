package com.grupo2.flysky.service;

import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.dto.requestDto.ReservationDto;
import com.grupo2.flysky.dto.responseDto.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IFlySkyService {

    /*
     *  ResponseDto no seria el objeto final de retorno.
     *
     */

    List<FlightDto> findAllFlights(); // Buscar vuelos

    TicketReservedDto buyTicket(Long id, ClientRequestDto client); // Comprar o Reservar un Boleto con el numero de Vuelo

    ResponseDto payment(Long idTicket, String paymentMethod); // Pagar la Compra o Reserva del Boleto

    List<TicketDto> findClient(Long idClient); // Buscar un cliente, para ver sus Datos e Historial

    Double calcularPrecioConDescuento(LocalDateTime fecha,Double precio);

    DailyReportDto findDailyReport(LocalDate date);
}
