package com.grupo2.flysky.controller;

import com.grupo2.flysky.dto.bodyDTO;
import com.grupo2.flysky.service.FlySkyInterfaceService;
import com.grupo2.flysky.service.FlyskyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class FlySkyController {
    /*
     *  Controlador para dividirnos tareas
     *
     *
     */

    private FlySkyInterfaceService service;
    public FlySkyController(FlyskyService service){
        this.service = service;
    }

    @GetMapping("/flights")
    public ResponseEntity<?> flights(){
        return null;
    }

    @GetMapping("/clients/{id}") // o "/customers/{id}"
    public ResponseEntity<?> findClient(@PathVariable Long id){
        return null;
    }

    @PostMapping("/reservation")
    public ResponseEntity<?> buyTicket(@RequestBody bodyDTO body){
        return null;
    }

    @PostMapping("/payments")
    public ResponseEntity<?> payment(@RequestBody bodyDTO body){
        return null;
    }

}
