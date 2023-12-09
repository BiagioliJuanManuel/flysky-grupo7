package com.grupo2.flysky.exception;

import com.grupo2.flysky.dto.exceptionDto.FlightExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationFail(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().stream()
                .map(er -> {
                    String message = ((FieldError)er).getField();
                    String cause = er.getDefaultMessage();
                    errors.put(message, cause);
                    return null;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataBaseIsEmptyException.class)
    public ResponseEntity<?> dataBaseIsEmpty(DataBaseIsEmptyException e){
        FlightExceptionDto flightExceptionDto = new FlightExceptionDto(404, e.getMessage());
        return new ResponseEntity<>(flightExceptionDto, HttpStatus.NOT_FOUND);
    }

}

