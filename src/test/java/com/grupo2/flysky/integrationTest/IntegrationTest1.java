package com.grupo2.flysky.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.entity.Client;
import com.grupo2.flysky.entity.Flight;
import com.grupo2.flysky.entity.Ticket;
import com.grupo2.flysky.repository.IClientRepository;
import com.grupo2.flysky.repository.IFlightRepository;
import com.grupo2.flysky.repository.ITicketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import static com.grupo2.flysky.utils.FactoryObjects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@DisplayName("Test de Integración")
public class IntegrationTest1 {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    IFlightRepository flightRepository;
    @Autowired
    IClientRepository clientRepository;
    @Autowired
    ITicketRepository ticketRepository;


    @Test
    @DisplayName("Test integracion de findAllFLights")
    @Order(1)
    void findAllFlightsOk() throws Exception {
        //ARRANGE
        flightRepository.save(unVuelo());

        //ACT
        MvcResult respuesta = mockMvc.perform(get("/v1/api/flights"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idFlight").value("1"))
                .andReturn();

        //ASSERT
        assertEquals("application/json",respuesta.getResponse().getContentType());

    }

    @Test
    @DisplayName("Test de reserva")
    @Order(2)
    void reservationTestOk() throws Exception {
          mockMvc.perform(post("/v1/api/reservation").param("idFlight","1").content("" +
                "{\n" +
                "  \"name\":\"Juan manuel\",\n" +
                "  \"documentNumber\":20101123,\n" +
                "  \"email\":\"asdasa@sdas.com\",\n" +
                "  \"age\":27,\n" +
                "  \"phoneNumber\":\"1580901234\"\n" +
                "}").contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("name").value("Juan manuel"))
                .andExpect(jsonPath("documentNumber").value("20101123"))
                .andReturn();

    }

    @Test
    @DisplayName("Test payment OK")
    @Order(3)
    void paymentIsOk() throws Exception {
        //ARRANGE
        Client client = unCliente();
        Flight flight = unVuelo();
        Ticket ticket = unTicket();
        clientRepository.save(client);
        flightRepository.save(flight);
        ticket.setClient(client);
        ticket.setFlight(flight);
        ticketRepository.save(ticket);

        //ACT
//        MvcResult respuesta = mockMvc.perform(get("/v1/api/payments"))
        mockMvc.perform(post("/v1/api/payments").param("idTicket","1").param("paymentMethod","Tarjeta"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("message").value("El ticket fue pagado con éxito"))
                .andReturn();

        //ASSERT
//        assertEquals("application/json",respuesta.getResponse().getContentType());

    }

    @Test
    @DisplayName("Test de payments (no hay ticket)")
    @Order(4)
    void paymentIsNotOk() throws Exception {
        //ARRANGE

        //ACT
//        MvcResult respuesta = mockMvc.perform(get("/v1/api/payments"))
        mockMvc.perform(post("/v1/api/payments").param("idTicket","99").param("paymentMethod","Tarjeta"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("error").value("No existe ticket con id: 99"))
                .andReturn();

        //ASSERT
//        assertEquals("application/json",respuesta.getResponse().getContentType());

    }

    @Test
    @DisplayName("Test de reserva (vuelo no encontrado)")
    @Order(5)
    void reservationTestNotFoundFlightOk() throws Exception {

        ClientRequestDto requestDto = unClienteRequestDto();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE,false)
                        .writer();

        String payload = writer.writeValueAsString(requestDto);

        mockMvc.perform(post("/v1/api/reservation").param("idFlight","9999")
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("status").value("404"))
                .andExpect(jsonPath("error").value("No hay vuelo con id: 9999"))
                .andReturn();

    }

    @Test
    @DisplayName("Test historial de cliente")
    @Order(6)
    void findClientTicketsTestOk() throws Exception {
        //ARRENGE
        Client client = unCliente();
        Flight flight = unVuelo();
        Flight flight2 = otroVuelo();
        Ticket ticket = unTicket();
        Ticket ticket2 = otroTicket();
        clientRepository.save(client);
        flightRepository.save(flight);
        flightRepository.save(flight2);

        ticket.setClient(client);
        ticket2.setClient(client);
        ticket.setFlight(flight);
        ticket2.setFlight(flight2);
        ticketRepository.save(ticket);
        ticketRepository.save(ticket2);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String respuestaEsperadaJSON = "[{\"flight\":{\"idFlight\":1," +
                "\"flightDate\":\""+ LocalDateTime.now().withSecond(0).format(formatter) +"\"," +
                "\"airline\":\"Airline1\"," +
                "\"price\":80000.0," +
                "\"availableSeats\":30," +
                "\"origin\":\"Ciudad1\"," +
                "\"destination\":\"Ciudad2\"}," +
                "\"client\":{\"name\":\"Juan\",\"email\":\"adfasd@adfa.com\",\"age\":22,\"phoneNumber\":\"123456789\"}," +
                "\"finalPrice\":40000.0," +
                "\"reservation\":false," +
                "\"paymentDate\":\""+ LocalDate.now() +"\"}," +
                "{\"flight\":{\"idFlight\":2," +
                "\"flightDate\":\""+ LocalDateTime.now().withSecond(0).format(formatter) +"\"," +
                "\"airline\":\"Airline2\"," +
                "\"price\":100000.0," +
                "\"availableSeats\":20," +
                "\"origin\":\"Ciudad3\"," +
                "\"destination\":\"Ciudad4\"}," +
                "\"client\":{\"name\":\"Juan\",\"email\":\"adfasd@adfa.com\",\"age\":22,\"phoneNumber\":\"123456789\"}," +
                "\"finalPrice\":40000.0," +
                "\"reservation\":false," +
                "\"paymentDate\":\""+ LocalDate.now() +"\"}]";

        MvcResult actual = mockMvc.perform(get("/v1/api/clients/{id}","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertEquals(respuestaEsperadaJSON,actual.getResponse().getContentAsString());
    }

}
