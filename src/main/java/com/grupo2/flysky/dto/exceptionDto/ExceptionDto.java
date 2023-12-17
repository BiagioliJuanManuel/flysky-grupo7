package com.grupo2.flysky.dto.exceptionDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDto {
    private int status;
    private String error;

}
