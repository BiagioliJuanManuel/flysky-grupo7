package com.grupo2.flysky.service;

import com.grupo2.flysky.dto.responseDto.FlightDto;
import com.grupo2.flysky.dto.responseDto.ResponseDto;
import com.grupo2.flysky.entity.Flight;
import com.grupo2.flysky.exception.DataBaseIsEmptyException;
import com.grupo2.flysky.repository.IFlightRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlyskyService implements IFlySkyService {

    private final IFlightRepository repository;

    ModelMapper mapper = new ModelMapper();

    public FlyskyService(IFlightRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FlightDto> findAllFlights() {
        List<Flight> list = repository.findAll();
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
    public ResponseDto findClient(Long idClient) {
        return null;
    }
}
