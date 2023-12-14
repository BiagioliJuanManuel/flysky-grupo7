package com.grupo2.flysky.utils;

import com.grupo2.flysky.dto.responseDto.FlightDto;
import com.grupo2.flysky.entity.Flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FactoryObjects {

    //lista de FlightDto
    public static List<FlightDto> listFlightsDto(){

        FlightDto flightDto1 = new FlightDto(1L,
                LocalDateTime.of(2023, 12, 31, 10, 30),  // Fecha y hora del vuelo
                "FlySky Airlines",
                200000D,
                150,
                "CityA",
                "CityB"
        );
        FlightDto flightDto2 = new FlightDto(2L,
                LocalDateTime.of(2023, 12, 31, 15, 45),  // Fecha y hora del vuelo
                "AirTravel Express",
                180000D,
                100,
                "CityC",
                "CityD"
        );

        List<FlightDto> flightDtoList = new ArrayList<>();
        flightDtoList.add(flightDto1);
        flightDtoList.add(flightDto2);

        return flightDtoList;
    }

    //lista de Flight
    public static List<Flight> listFlights(){

        List<Flight> flightList = new ArrayList<>();

        Flight Flight1 = new Flight();
        Flight Flight2 = new Flight();

        Flight1.setIdFlight(1L);
        Flight1.setFlightDate(LocalDateTime.of(2023, 12, 31, 10, 30));
        Flight1.setAirline("FlySky Airlines");
        Flight1.setPrice(200000D);
        Flight1.setTotalSeats(200);
        Flight1.setAvailableSeats(150);
        Flight1.setOrigin("CityA");
        Flight1.setDestination("CityB");

        Flight2.setIdFlight(2L);
        Flight2.setFlightDate(LocalDateTime.of(2023, 12, 31, 15, 45));
        Flight2.setAirline("AirTravel Express");
        Flight2.setPrice(180000D);
        Flight2.setTotalSeats(180);
        Flight2.setAvailableSeats(100);
        Flight2.setOrigin("CityC");
        Flight2.setDestination("CityD");

        flightList.add(Flight1);
        flightList.add(Flight2);

        return flightList;
    }

}
