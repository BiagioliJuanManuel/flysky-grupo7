package com.grupo2.flysky.serviceTest;


import com.grupo2.flysky.dto.responseDto.FlightDto;
import com.grupo2.flysky.entity.Flight;
import com.grupo2.flysky.exception.DataBaseIsEmptyException;
import com.grupo2.flysky.repository.IFlightRepository;
import com.grupo2.flysky.service.FlyskyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static com.grupo2.flysky.utils.FactoryObjects.listFlights;
import static com.grupo2.flysky.utils.FactoryObjects.listFlightsDto;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightsFlyskyServiceTest {

    @Mock
    IFlightRepository repository;

    @Mock
    ModelMapper mapper;

    @InjectMocks
    FlyskyService service;


//    @Test
//    public void findAllFlightsOkTest(){
//        //ARRANGE
//        List<FlightDto> flightsDto = listFlightsDto();//metodo estatico para crear lista de objetos flightDto
//        List<Flight> flight = listFlights();
//
//        when(repository.findAll()).thenReturn(flight);
//        when(mapper.map(flight, FlightDto.class)).thenReturn((FlightDto) flightsDto);
//    }


}
