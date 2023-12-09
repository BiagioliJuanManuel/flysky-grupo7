package com.grupo2.flysky.dto.responseDto;

import com.grupo2.flysky.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String name;

    private String email;

    private Integer age;

    private String phoneNumber;

    private TicketDto ticket;
}
