package com.grupo2.flysky.controllerTest;

import com.grupo2.flysky.controller.FlySkyController;
import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.dto.responseDto.*;
import com.grupo2.flysky.service.FlyskyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.grupo2.flysky.utils.FactoryObjects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ControllerTestsConMock {
    @Mock
    FlyskyService flyskyService;

    @InjectMocks
    FlySkyController flyskyController;

    @Test
    @DisplayName("Endpoint GET/flights")
    void getAllFlightTestOk() {
        //ARRANGE
        List<FlightDto> bodyRespuesta = listFlightsDto();
        ResponseEntity<?> respuestaEsperada = new ResponseEntity<>(bodyRespuesta, HttpStatus.OK);

        when(flyskyService.findAllFlights()).thenReturn(bodyRespuesta);

        //ACT
        ResponseEntity<?> respuestaActual = flyskyController.getAllFlights();

        //ASSERT
        assertEquals(respuestaEsperada,respuestaActual);
    }

    @Test
    @DisplayName("Endpoint POST/reservation")
    void buyTicketReservationOk() {
        //ARRANGE

        ClientRequestDto clienteDto = unClienteRequestDto();
        TicketReservedDto bodyRespuesta = unTicketReservedDto();
        ResponseEntity<?> respuestaEsperada = new ResponseEntity<>(bodyRespuesta, HttpStatus.OK);

        when(flyskyService.buyTicket(anyLong(),any())).thenReturn(unTicketReservedDto());
        //ACT
        ResponseEntity<?> respuestaActual = flyskyController.buyTicket(1L,clienteDto);

        //ASSERT
        assertEquals(respuestaEsperada,respuestaActual);
    }

    @Test
    @DisplayName("Endpoint POST/payments")
    void paymentOfReservationOk() {
        //ARRANGE
        ResponseDto bodyEsperado = new ResponseDto("El Ticket fue pagado con éxito");
        ResponseEntity<?> respuestaEsperada = new ResponseEntity<>(bodyEsperado, HttpStatus.OK);

        when(flyskyService.payment(anyLong(),anyString())).thenReturn(bodyEsperado);

        //ACT
        ResponseEntity<?> respuestaActual = flyskyController.payment(anyLong(),anyString());

        //ASSERT
        assertEquals(respuestaEsperada,respuestaActual);
    }

    @Test
    @DisplayName("Endpoint GET/clients/{id}")
    void findClientHistorialOk() {
        //ARRANGE
        List<TicketDto> bodyEsperado = unaListaDeTicketDto();
        ResponseEntity<?> respuestaEsperada = new ResponseEntity<>(bodyEsperado, HttpStatus.OK);

        when(flyskyService.findClient(anyLong())).thenReturn(bodyEsperado);

        //ACT
        ResponseEntity<?> respuestaActual = flyskyController.findClient(anyLong());

        //ASSERT
        assertEquals(respuestaEsperada,respuestaActual);
    }

    @Test
    @DisplayName("Endpoint GET/reports/{dailyDate}")
    void getReportsOk() {
        //ARRANGE
        DailyReportDto bodyEsperado = new DailyReportDto();
        ResponseEntity<?> respuestaEsperada = new ResponseEntity<>(bodyEsperado, HttpStatus.OK);

        when(flyskyService.findDailyReport(any())).thenReturn(bodyEsperado);

        //ACT
        ResponseEntity<?> respuestaActual = flyskyController.getReport(any());

        //ASSERT
        assertEquals(respuestaEsperada,respuestaActual);
    }
}
