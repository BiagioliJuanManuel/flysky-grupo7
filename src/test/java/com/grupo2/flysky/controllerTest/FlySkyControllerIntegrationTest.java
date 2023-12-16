package com.grupo2.flysky.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.dto.responseDto.TicketReservedDto;
import com.grupo2.flysky.entity.Client;
import com.grupo2.flysky.entity.Flight;
import com.grupo2.flysky.repository.IClientRepository;
import com.grupo2.flysky.repository.IFlightRepository;
import com.grupo2.flysky.service.IFlySkyService;
import com.grupo2.flysky.utils.FactoryObjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.grupo2.flysky.utils.FactoryObjects.unVuelo;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class FlySkyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IFlySkyService flySkyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Test
    public void testBuyTicketIntegration() throws Exception {
        // Configuración del comportamiento simulado del servicio
        ClientRequestDto requestDto = new ClientRequestDto(
                "NombreCliente",
                123456789L,
                "correo@example.com",
                25,
                "1234567890"
        );

        flightRepository.save(unVuelo());

        // Configuración del comportamiento simulado del servicio
        TicketReservedDto mockedResult = createMockedResult();

        Mockito.when(flySkyService.buyTicket(Mockito.anyLong(), any(ClientRequestDto.class)))
                .thenReturn(mockedResult);


        // Ejecutar la solicitud y verificar el resultado
        mockMvc.perform(post("/v1/api/reservation")
                        .param("idFlight", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // Verificar que los vuelos se han creado en la base de datos
        Optional<Flight> flight = flightRepository.findById(1L);

        // Realizar aserciones sobre los resultados obtenidos
        assertTrue(flight.isPresent());

    }

    // Método para crear el resultado simulado del servicio
    private TicketReservedDto createMockedResult() {
        return new TicketReservedDto(
                1L,                  // Id del ticket
                "NombreCliente",     // Nombre del cliente
                123456789L,          // Número de documento del cliente
                500.0,               // Precio final del boleto
                "CiudadOrigen",      // Ciudad de origen del vuelo
                "CiudadDestino"      // Ciudad de destino del vuelo
        );
    }
}
