package com.grupo2.flysky.serviceTest;


import com.grupo2.flysky.dto.responseDto.FlightDto;
import com.grupo2.flysky.entity.Flight;
import com.grupo2.flysky.repository.IFlightRepository;
import com.grupo2.flysky.service.FlyskyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.grupo2.flysky.utils.FactoryObjects.listFlights;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FlightsFlyskyServiceTest2 {

    @Mock
    private IFlightRepository flightRepository;

    @InjectMocks
    private FlyskyService flyskyService;

    @Test
    void testFindAllFlights() {
        // Mock de datos de vuelo
//        Flight flight1 = new Flight();
//        flight1.setIdFlight(1L);
//        flight1.setFlightDate(LocalDateTime.now());
//        flight1.setAirline("Aerolínea Ejemplo");
//        flight1.setPrice(500.0);
//        flight1.setTotalSeats(150);
//        flight1.setAvailableSeats(100);
//        flight1.setOrigin("Ciudad Origen");
//        flight1.setDestination("Ciudad Destino");
//        Flight flight2 = new Flight();
//        flight2.setIdFlight(2L);
//        flight2.setFlightDate(LocalDateTime.now());
//        flight2.setAirline("Aerolínea Ejemplo");
//        flight2.setPrice(500.0);
//        flight2.setTotalSeats(150);
//        flight2.setAvailableSeats(100);
//        flight2.setOrigin("Ciudad Origen");
//        flight2.setDestination("Ciudad Destino");
//        List<Flight> mockFlights = Arrays.asList(flight1, flight2);
        List<Flight> mockFlights = listFlights();

        // Configuración del comportamiento simulado del repositorio
        when(flightRepository.findAll()).thenReturn(mockFlights);

        // Llamada al servicio
        List<FlightDto> result = flyskyService.findAllFlights();

        // Verificación de resultados
        assertEquals(2, result.size());

    }
}
