package com.grupo2.flysky.service;

import com.grupo2.flysky.dto.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public class FlyskyService implements FlySkyInterfaceService{
    @Override
    public ResponseDto findAllFlights() {
        return null;
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
