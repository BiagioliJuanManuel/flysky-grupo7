package com.grupo2.flysky.controller;

import com.grupo2.flysky.dto.requestDto.BodyDto;
import com.grupo2.flysky.dto.requestDto.TicketDto;
import com.grupo2.flysky.service.IFlySkyService;
import com.grupo2.flysky.service.FlyskyService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
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
    public ResponseEntity<?> flights(){
        return new ResponseEntity<>(service.findAllFlights() ,HttpStatus.OK);
    }

    //endpoint para hacer una reservación
    @PostMapping("/reservation")
    public ResponseEntity<?> buyTicket(@RequestBody TicketDto ticket){
        return new ResponseEntity<>(service.buyTicket(ticket), HttpStatus.OK);
    }

    //endpoint para realizar pago
    @PostMapping("/payments")
    public ResponseEntity<?> payment(@RequestBody BodyDto body){
        return null;
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
    @GetMapping("/reports")
    public ResponseEntity<?> getReports(){
        return null;
    }


}
