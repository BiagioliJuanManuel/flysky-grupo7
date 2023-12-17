package com.grupo2.flysky.serviceTest;

import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.dto.responseDto.ResponseDto;
import com.grupo2.flysky.dto.responseDto.TicketReservedDto;
import com.grupo2.flysky.entity.Ticket;
import com.grupo2.flysky.exception.DataBaseIsEmptyException;
import com.grupo2.flysky.repository.IFlightRepository;
import com.grupo2.flysky.service.FlyskyService;
import com.grupo2.flysky.service.IFlySkyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FlySkyBuyTicketTest {
    @Autowired
    public IFlySkyService flySkyService;

    @Mock
    private IFlightRepository flightRepository;

    /*
     *  Autor: Diego Moreno Rico
     */
    @Test
    void testBuyTicket_FlightNotFound_Exception() {
        // Caso de prueba donde el vuelo no se encuentra en la base de datos

        // Mock de datos
        Long flightId = 1L;
        ClientRequestDto clientRequestDto = new ClientRequestDto("Cliente Prueba", 123456789L, "clienteprueba@example.com", 25, "1234567890");

        // Configuración del comportamiento simulado del repositorio
        when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        // Verificación de excepción
        assertThrows(DataBaseIsEmptyException.class, () -> flySkyService.buyTicket(flightId, clientRequestDto));
    }

    @Test
    public void calcularDescuentoOk(){
        //Arrange
        Double resultadoEsperado = 72000D;
        //Act
        Double resultadoActual = flySkyService.calcularPrecioConDescuento(LocalDateTime.of(2024,01,17,10,00,00),80000D);
        //Assert
        //no siempre devuelve el id del boleto correcto en el response
        assertEquals(resultadoEsperado,resultadoActual);
    }
}
