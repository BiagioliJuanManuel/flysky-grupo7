package com.grupo2.flysky.service;

import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.dto.responseDto.ClientDto;
import com.grupo2.flysky.dto.responseDto.FlightDto;
import com.grupo2.flysky.dto.responseDto.ResponseDto;
import com.grupo2.flysky.dto.responseDto.TicketDto;
import com.grupo2.flysky.entity.Client;
import com.grupo2.flysky.entity.Flight;
import com.grupo2.flysky.entity.Ticket;
import com.grupo2.flysky.exception.DataBaseIsEmptyException;
import com.grupo2.flysky.repository.IClientRepository;
import com.grupo2.flysky.repository.IFlightRepository;

import com.grupo2.flysky.repository.ITicketRepository;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlyskyService implements IFlySkyService {

    private final IFlightRepository flightRepository;
    private final IClientRepository clientRepository;
    private final ITicketRepository ticketRepository;
    private final ModelMapper mapper;

    public FlyskyService(IFlightRepository flightRepository, IClientRepository clientRepository, ITicketRepository ticketRepository) {
        this.mapper = new ModelMapper();
        this.flightRepository = flightRepository;
        this.clientRepository = clientRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<FlightDto> findAllFlights() {
        List<Flight> list = flightRepository.findAll();

        if (list.isEmpty()){
            throw new DataBaseIsEmptyException("No se encontraron vuelos disponibles.");
        }

        return list.stream()
                .map(f -> mapper.map(f, FlightDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto buyTicket(Long id, ClientRequestDto client){
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        Client nuevo = mapper.map(client,Client.class);
        Client frecuente = new Client();
        Ticket boleto = new Ticket();

        Optional<Client> clienteExiste = clientRepository.findAll().stream().filter(client1 -> client1.getDocumentNumber().equals(nuevo.getDocumentNumber())).findFirst();

//        if (clientRepository.findAll().stream().anyMatch(c -> c.getDocumentNumber().equals(nuevo.getDocumentNumber()))){
        if (optionalFlight.isEmpty()){
            throw new DataBaseIsEmptyException("No hay vuelo con id: "+id);
        }
        if (clienteExiste.isPresent()){
            frecuente = clienteExiste.get();
            boleto.setClient(frecuente);
        }else {
            clientRepository.save(nuevo);
            boleto.setClient(nuevo);
        }
        boleto.setFlight(optionalFlight.get());
        ticketRepository.save(boleto);

        return new ResponseDto("El cliente: "+nuevo.getName()+" reservo un boleto: "+boleto.getIdTicket());
    }

    @Override
    public ResponseDto payment(Long idTicket) {
        return null;
    }

    @Override
    public List<TicketDto> findClient(Long idClient) {
        Optional<Client> optionalClient = clientRepository.findById(idClient);

        if (optionalClient.isEmpty()){
            throw new DataBaseIsEmptyException("No se encontró el cliente solicitado.");
        }

        Client client = optionalClient.get();

        List<TicketDto> listTickets = client.getTicket().stream()
                .map(ticket -> mapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
               return listTickets;
    }
}
