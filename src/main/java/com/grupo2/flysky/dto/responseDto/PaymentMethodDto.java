package com.grupo2.flysky.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDto {
    private String paymentMode;

    private LocalDateTime paymentDate;
}
