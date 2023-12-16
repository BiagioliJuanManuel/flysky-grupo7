package com.grupo2.flysky.serviceTest;

import com.grupo2.flysky.controller.FlySkyController;
import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.dto.responseDto.TicketReservedDto;
import com.grupo2.flysky.entity.Client;
import com.grupo2.flysky.entity.Flight;
import com.grupo2.flysky.entity.Ticket;
import com.grupo2.flysky.exception.DataBaseIsEmptyException;
import com.grupo2.flysky.repository.IClientRepository;
import com.grupo2.flysky.repository.IFlightRepository;
import com.grupo2.flysky.repository.ITicketRepository;
import com.grupo2.flysky.service.FlyskyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import java.util.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FlySkyBuyTicketTest2 {

    @Mock
    private IFlightRepository flightRepository;

    @Mock
    private IClientRepository clientRepository;

    @Mock
    private ITicketRepository ticketRepository;

    @InjectMocks
    private FlyskyService flyskyService;

    @InjectMocks
    private FlySkyController flySkyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuyTicket_Success() {
        // Mock de datos
        Long flightId = 1L;
        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setName("Nombre Apellido");
        clientRequestDto.setDocumentNumber(123456789L);
        clientRequestDto.setEmail("correo@example.com");
        clientRequestDto.setAge(25);
        clientRequestDto.setPhoneNumber("1234567890");
        Flight mockFlight = new Flight(1L, LocalDateTime.now(), "Aerolínea Ejemplo", 500.0, 150, 100, "Ciudad Origen", "Ciudad Destino", null);
        Client mockClient = new Client();
        mockClient.setIdClient(1L);
        mockClient.setName("Nombre Apellido");
        mockClient.setDocumentNumber(123456789L);
        mockClient.setEmail("correo@example.com");
        mockClient.setAge(25);
        mockClient.setPhoneNumber("1234567890");

        // Configuración del comportamiento simulado del repositorio
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(mockFlight));
        List<Client> mockClients = Arrays.asList(  // Agrega "Arrays."
                new Client(),
                new Client()
        );

        // Configuración del comportamiento simulado del repositorio
        when(clientRepository.findAll()).thenReturn(mockClients);

        // Llamada al servicio
        TicketReservedDto result = flyskyService.buyTicket(flightId, clientRequestDto);

        // Verificación de resultados
        assertNotNull(result);
        assertEquals(mockClient.getName(), result.getName());
        assertEquals(mockClient.getDocumentNumber(), result.getDocumentNumber());
        assertEquals(mockFlight.getPrice(), result.getFinalPrice());
        assertEquals(mockFlight.getOrigin(), result.getOrigin());
        assertEquals(mockFlight.getDestination(), result.getDestination());
    }

    @Test
    void testBuyTicket_ClientAlreadyExists_Success() {
        // Caso de prueba donde el cliente ya existe en la base de datos

        // Mock de datos
        Long flightId = 1L;
        ClientRequestDto clientRequestDto = new ClientRequestDto("Cliente Existente", 123456789L, "clienteexistente@example.com", 30, "1234567890");
        Flight mockFlight = new Flight(1L, LocalDateTime.now(), "Aerolínea Ejemplo", 500.0, 150, 100, "Ciudad Origen", "Ciudad Destino", null);
        Client existingClient = new Client();

        // Configuración del comportamiento simulado del repositorio
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(mockFlight));
        when(clientRepository.findAll()).thenReturn(List.of(existingClient));

        // Llamada al servicio
        TicketReservedDto result = flyskyService.buyTicket(flightId, clientRequestDto);

        // Verificación de resultados
        assertNotNull(result);
        assertEquals(existingClient.getName(), result.getName());
        assertEquals(existingClient.getDocumentNumber(), result.getDocumentNumber());
        assertEquals(mockFlight.getPrice(), result.getFinalPrice());
        assertEquals(mockFlight.getOrigin(), result.getOrigin());
        assertEquals(mockFlight.getDestination(), result.getDestination());
    }


    @Test
    void testBuyTicket_FlightNotFound_Exception() {
        // Caso de prueba donde el vuelo no se encuentra en la base de datos

        // Mock de datos
        Long flightId = 1L;
        ClientRequestDto clientRequestDto = new ClientRequestDto("Cliente Prueba", 123456789L, "clienteprueba@example.com", 25, "1234567890");

        // Configuración del comportamiento simulado del repositorio
        when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        // Verificación de excepción
        assertThrows(DataBaseIsEmptyException.class, () -> flyskyService.buyTicket(flightId, clientRequestDto));
    }

}