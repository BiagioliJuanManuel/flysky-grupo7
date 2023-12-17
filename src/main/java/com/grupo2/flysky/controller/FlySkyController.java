package com.grupo2.flysky.controller;

import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.service.IFlySkyService;
import com.grupo2.flysky.service.FlyskyService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.PastOrPresent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/api")
@Validated
public class FlySkyController {
    /*
     *  Controlador para dividirnos tareas
     *
     *
     */

    private final IFlySkyService service;

    public FlySkyController(FlyskyService service){
        this.service = service;
    }

    //endpoint para ver lista de vuelos
    @GetMapping("/flights")
    public ResponseEntity<?> getAllFlights(){
        return new ResponseEntity<>(service.findAllFlights() ,HttpStatus.OK);
    }

    //endpoint para hacer una reservación
    @PostMapping("/reservation")
    public ResponseEntity<?> buyTicket(@RequestParam Long idFlight, @RequestBody @Valid ClientRequestDto clientRequestDto){
        return new ResponseEntity<>(service.buyTicket(idFlight,clientRequestDto), HttpStatus.OK);
    }

    //endpoint para realizar pago
    @PostMapping("/payments")
    public ResponseEntity<?> payment(@RequestParam Long idTicket, @RequestParam String paymentMethod){
        return new ResponseEntity<>(service.payment(idTicket,paymentMethod), HttpStatus.OK);
    }

    //acceder a la información de un cliente (historial de reservas, preferencias de viaje y detalles de contacto)
    @GetMapping("/clients/{id}") // o "/customers/{id}"
    public ResponseEntity<?> findClient(
            @PathVariable("id")
            @NotNull(message = "El ID no puede ser nulo.")
            @Min(value = 1, message = "El campo ID debe ser mayor a 0.")
            Long id){
        return new ResponseEntity<>(service.findClient(id), HttpStatus.OK);
    }

    //obtener número de ventas realizadas y los ingresos generados (para informes diarios)
    @GetMapping("/reports/{dailyDate}")
    public ResponseEntity<?> getReport(
            @PathVariable("dailyDate")
            @NotNull(message = "Por favor ingrese la fecha correspondiente al dia del cual quiere obtener los reportes")
            @PastOrPresent(message = "No existen reportes en base a fechas futuras")
            LocalDate date){
        return new ResponseEntity<>(service.findDailyReport(date), HttpStatus.OK);
    }


}
