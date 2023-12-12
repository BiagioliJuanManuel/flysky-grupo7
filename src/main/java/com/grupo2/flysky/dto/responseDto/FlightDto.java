package com.grupo2.flysky.dto.responseDto;

import com.grupo2.flysky.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    private Long idFlight;

    private LocalDateTime flightDate;

    private String airline;

    private Double reservePrice;

    private Integer availableSeats;

    private String origin;

    private String destination;

}
