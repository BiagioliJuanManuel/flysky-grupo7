package com.grupo2.flysky.controllerTest;

import com.grupo2.flysky.controller.FlySkyController;
import com.grupo2.flysky.dto.responseDto.FlightDto;
import com.grupo2.flysky.service.FlyskyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.grupo2.flysky.utils.FactoryObjects.listFlightsDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllFlightsTest {

    @Mock
    FlyskyService service;

    @InjectMocks
    FlySkyController controller;

    @Test
    void getAllFlightsOkTest(){

        //ARRANGE
        List<FlightDto> expectedFlights = listFlightsDto();//metodo estatico para crear lista de objetos flightDto

        when(service.findAllFlights()).thenReturn(expectedFlights);

        //ACT
        ResponseEntity<?> actualResponse = controller.getAllFlights();

        //ASSERT
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedFlights, actualResponse.getBody());

    }

}
