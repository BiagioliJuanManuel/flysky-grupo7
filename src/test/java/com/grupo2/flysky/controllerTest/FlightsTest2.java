package com.grupo2.flysky.controllerTest;

import com.grupo2.flysky.controller.FlySkyController;
import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.dto.responseDto.ResponseDto;
import com.grupo2.flysky.dto.responseDto.TicketDto;
import com.grupo2.flysky.service.FlyskyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.grupo2.flysky.dto.responseDto.FlightDto;
import com.grupo2.flysky.dto.responseDto.TicketReservedDto;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static com.grupo2.flysky.utils.FactoryObjects.listFlightsDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightsTest2 {

    @Mock
    FlyskyService service;

    @InjectMocks
    FlySkyController controller;

    @Test
    void obtenerTodosLosVuelosOkTest() {
        // ARRANGE
        List<FlightDto> vuelosEsperados = listFlightsDto();
        when(service.findAllFlights()).thenReturn(vuelosEsperados);

        // ACT
        ResponseEntity<List<FlightDto>> respuestaActual = (ResponseEntity<List<FlightDto>>) controller.getAllFlights();

        // ASSERT
        assertEquals(HttpStatus.OK, respuestaActual.getStatusCode());

        // Verifica que el cuerpo de la respuesta sea una lista y contenga todos los elementos esperados
        assertNotNull(respuestaActual.getBody());
        List<FlightDto> vuelosActuales = respuestaActual.getBody();
        assertEquals(vuelosEsperados.size(), vuelosActuales.size());
        assertTrue(vuelosActuales.containsAll(vuelosEsperados));
    }

    @Test
    void comprarBoleto_Exitoso() {
        // ARRANGE
        TicketReservedDto resultadoSimulado = new TicketReservedDto(/* ... */);
        when(service.buyTicket(1L, new ClientRequestDto())).thenReturn(resultadoSimulado);

        // ACT
        ResponseEntity<TicketReservedDto> respuesta = (ResponseEntity<TicketReservedDto>) controller.buyTicket(1L, new ClientRequestDto());

        // ASSERT
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
    }

    @Test
    void pago_Exitoso() {
        // ARRANGE
        when(service.payment(1L, "credit_card")).thenReturn(new ResponseDto("Pago procesado exitosamente."));

        // ACT
        ResponseEntity<ResponseDto> respuesta = (ResponseEntity<ResponseDto>) controller.payment(1L, "credit_card");

        // ASSERT
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals("Pago procesado exitosamente.", respuesta.getBody().getMessage());
    }

    @Test
    void encontrarCliente_Exitoso() {
        // ARRANGE
        when(service.findClient(1L)).thenReturn(createMockedFindClientResponse());

        // ACT
        ResponseEntity<List<TicketDto>> respuesta = (ResponseEntity<List<TicketDto>>) controller.findClient(1L);

        // ASSERT
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
    }

    private List<TicketDto> createMockedFindClientResponse() {
        List<TicketDto> mockedResponse = new ArrayList<>();

        // Crear instancias de TicketDto y agregarlas a la lista
        TicketDto ticketDto1 = new TicketDto();
        TicketDto ticketDto2 = new TicketDto();

        mockedResponse.add(ticketDto1);
        mockedResponse.add(ticketDto2);

        return mockedResponse;
    }
}
