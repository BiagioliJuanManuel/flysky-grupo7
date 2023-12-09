package com.grupo2.flysky.dto.exceptionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightExceptionDto {
    private int status;
    private String error;

}
