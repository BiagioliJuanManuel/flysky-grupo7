package com.grupo2.flysky.service;

import com.grupo2.flysky.dto.responseDto.ClientDto;
import com.grupo2.flysky.dto.responseDto.FlightDto;
import com.grupo2.flysky.dto.responseDto.ResponseDto;
import com.grupo2.flysky.entity.Client;
import com.grupo2.flysky.entity.Flight;
import com.grupo2.flysky.exception.DataBaseIsEmptyException;
import com.grupo2.flysky.repository.IClientRepository;
import com.grupo2.flysky.repository.IFlightRepository;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlyskyService implements IFlySkyService {

    private final IFlightRepository flightRepository;
    private final IClientRepository clientRepository;
    private final ModelMapper mapper;

    public FlyskyService(IFlightRepository flightRepository, IClientRepository clientRepository) {
        this.mapper = new ModelMapper();
        this.flightRepository = flightRepository;
        this.clientRepository = clientRepository;
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
    public ResponseDto buyTicket(Long idFlight) {
        return null;
    }

    @Override
    public ResponseDto payment(Long idTicket) {
        return null;
    }

    @Override
    public ClientDto findClient(Long idClient) {
        Optional<Client> optionalClient = clientRepository.findById(idClient);

        if (optionalClient.isEmpty()){
            throw new DataBaseIsEmptyException("No se encontró el cliente solicitado.");
        }

        return mapper.map(optionalClient.get(), ClientDto.class);
    }
}
