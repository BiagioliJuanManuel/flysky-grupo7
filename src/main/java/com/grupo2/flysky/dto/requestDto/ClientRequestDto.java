package com.grupo2.flysky.dto.requestDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {

    @NotNull(message = "Ingrese nombre y apellido")
    @Size(max = 50,message = "El nombre y apellido no puede tener más de 50 caracteres")
    private String name;

//    @Size(max = 9,min = 7,message = "Ingrese un documento valido entre 7 y 9 caracteres")
    @Min(value = 5000000,message = "El numero de documento no puede ser inferior a 5 millones")
    @Max(value = 300000000,message = "El numero de documento no puede ser superior a 300 millones")
    private Long documentNumber;

    @Email(message = "Ingrese un email valido")
    private String email;

    @Min(value = 10,message = "Un menor de 10 no puede viajar sin autorización y acompañamiento de un mayor")
    @Max(value = 90,message = "Un mayor de 90 años, necesita un certificado de salud")
    private Integer age;

    @Size(min = 10, max = 14, message = "Ingrese un numero de teléfono valido y con su característica nacional")
    private String phoneNumber;
}
