package com.grupo2.flysky.serviceTest;

import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.dto.responseDto.ResponseDto;
import com.grupo2.flysky.entity.Ticket;
import com.grupo2.flysky.service.FlyskyService;
import com.grupo2.flysky.service.IFlySkyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FlySkyBuyTicketTest {
    @Autowired
    public IFlySkyService flySkyService;

//    public FlySkyBuyTicketTest(FlyskyService flySkyService){
//        this.flySkyService = flySkyService;
//    }

    @Test
    public void buyTicketOk(){
        //Arrange
        ClientRequestDto cliente = new ClientRequestDto("Pedro Perez",
                1234567L,
                "asdf@asdf.com",
                30,
                "1512341234");
        Long idDelVuelo = 1L;
        Ticket boleto = new Ticket();
        boleto.setIdTicket(1L);
        ResponseDto resultadoEsperado = new ResponseDto("El cliente: "+cliente.getName()+" reservo un boleto: "+boleto.getIdTicket());
        //Act
        ResponseDto resultadoActual = flySkyService.buyTicket(1L,cliente);
        //Assert

        //no siempre devuelve el id del boleto correcto en el response
        assertEquals(resultadoEsperado,resultadoActual);
    }
}
