package com.grupo2.flysky.repositoryTest;

import com.grupo2.flysky.entity.Client;
import com.grupo2.flysky.entity.Flight;
import com.grupo2.flysky.entity.Ticket;
import com.grupo2.flysky.repository.IClientRepository;
import com.grupo2.flysky.repository.IFlightRepository;
import com.grupo2.flysky.repository.ITicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static com.grupo2.flysky.utils.FactoryObjects.*;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class ReposTest {
    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private ITicketRepository ticketRepository;



    @Test
    public void testGuardarYRecuperarCliente(){
        //ARRANGE
        Client cliente = unCliente();
        //ACT
//        when(clientRepository.save(cliente)).thenReturn(cliente);
        clientRepository.save(cliente);
        //ASSERT
        Optional<Client> optionalCliente = clientRepository.findById(cliente.getIdClient());
//        Client actual = new Client();
//        if(optionalCliente.isPresent()) {
//            actual = optionalCliente.get();
//        }
        assertTrue(optionalCliente.isPresent());
        assertEquals(cliente,optionalCliente.get());
    }

    @Test
    public void testBorrarCliente() {
        // ARRANGE
        Client cliente = unCliente();
        clientRepository.save(cliente);

        // ACT
        // Eliminar la entidad y verificar que ya no exista
        clientRepository.delete(cliente);

        // ASSERT
        assertFalse(clientRepository.existsById(cliente.getIdClient()));
    }

    @Test
    public void GuardarYRecuperarVuelo(){
        // ARRANGE
        Flight vuelo = unVuelo();
        // ACT
        flightRepository.save(vuelo);
        // ASSERT
        Optional<Flight> optionalFlight = flightRepository.findById(vuelo.getIdFlight());
        assertTrue(optionalFlight.isPresent());
        assertEquals(vuelo,optionalFlight.get());
    }

    @Test
    public void eliminarVuelo(){
        // ARRANGE
        Flight vuelo = unVuelo();
        flightRepository.save(vuelo);
        // ACT
        flightRepository.delete(vuelo);
        // ASSERT
        assertFalse(flightRepository.existsById(vuelo.getIdFlight()));
    }

    @Test
    public void GuardarYRecuperarTicket(){
        // ARRANGE
        Flight vuelo = unVuelo();
        Client cliente = unCliente();
        flightRepository.save(vuelo);
        clientRepository.save(cliente);
        // ACT
        Ticket ticket = new Ticket(1L,40000D,"Tarjeta", LocalDate.now(),false, cliente, vuelo);;
        ticketRepository.save(ticket);
        // ASSERT
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticket.getIdTicket());
        assertTrue(optionalTicket.isPresent());
        assertEquals(ticket,optionalTicket.get());
    }

    @Test
    public void eliminarTicket(){
        // ARRANGE
        Flight vuelo = unVuelo();
        Client cliente = unCliente();
        flightRepository.save(vuelo);
        clientRepository.save(cliente);
        // ACT
        Ticket ticket = unTicket();
        ticketRepository.save(ticket);
        // ACT
        ticketRepository.delete(ticket);
        // ASSERT
        assertFalse(ticketRepository.existsById(ticket.getIdTicket()));
    }


}
