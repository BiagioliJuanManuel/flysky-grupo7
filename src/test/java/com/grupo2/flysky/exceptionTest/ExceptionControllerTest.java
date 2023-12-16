package com.grupo2.flysky.exceptionTest;

import com.grupo2.flysky.dto.exceptionDto.ExceptionDto;
import com.grupo2.flysky.exception.DataBaseIsEmptyException;
import com.grupo2.flysky.exception.ExceptionController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ExceptionControllerTest {

    @Autowired
    ExceptionController exceptionController;

    @Test
    @DisplayName("Error de validacion")
    void validationExceptionOkTest(){
        //ARRANGE
        ExceptionDto error = new ExceptionDto(any(),any());
        MethodArgumentNotValidException argumentoSut = new MethodArgumentNotValidException(any(),any());
        ResponseEntity<?> esperado = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

        //ACT
        ResponseEntity<?> actual = exceptionController.validationFail(argumentoSut);

        //ASSERT
        assertEquals(esperado,actual);

    }

    @Test
    @DisplayName("Error base de datos vacia")
    void dataBaseIsEmptyExceptionOkTest(){
        //ARRANGE
        ExceptionDto error = new ExceptionDto(404,"mensaje random");
        DataBaseIsEmptyException argumentoSut = new DataBaseIsEmptyException("mensaje random");
        ResponseEntity<?> esperado = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        //ACT
        ResponseEntity<?> actual = exceptionController.dataBaseIsEmpty(argumentoSut);

        //ASSERT
        assertEquals(esperado,actual);
    }
}
