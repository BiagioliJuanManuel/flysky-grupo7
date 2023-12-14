package com.grupo2.flysky.serviceTest;

import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.dto.responseDto.ResponseDto;
import com.grupo2.flysky.dto.responseDto.TicketReservedDto;
import com.grupo2.flysky.entity.Ticket;
import com.grupo2.flysky.service.FlyskyService;
import com.grupo2.flysky.service.IFlySkyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FlySkyBuyTicketTest {
    @Autowired
    public IFlySkyService flySkyService;

//    public FlySkyBuyTicketTest(FlyskyService flySkyService){
//        this.flySkyService = flySkyService;
//    }

//    @Test
//    public void buyTicketOk(){
//        //Arrange
//        ClientRequestDto cliente = new ClientRequestDto("Pedro Perez",
//                1234567L,
//                "asdf@asdf.com",
//                30,
//                "1512341234");
//        Long idDelVuelo = 1L;
//        Ticket boleto = new Ticket();
//        boleto.setIdTicket(1L);
//        TicketReservedDto resultadoEsperado;
//        //Act
//        TicketReservedDto resultadoActual = flySkyService.buyTicket(1L,cliente);
//        //Assert
//
//        //no siempre devuelve el id del boleto correcto en el response
//        assertEquals(resultadoEsperado,resultadoActual);
//    }
    @Test
    public void calcularDescuentoOk(){
        //Arrange


        Double resultadoEsperado = 72000D;
        //Act

        Double resultadoActual = flySkyService.calcularPrecioConDescuento(LocalDateTime.of(2024,01,17,10,00,00),80000D);
        //Assert

        //no siempre devuelve el id del boleto correcto en el response
        assertEquals(resultadoEsperado,resultadoActual);
    }
}
