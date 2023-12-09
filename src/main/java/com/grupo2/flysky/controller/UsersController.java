package com.grupo2.flysky.controller;

import com.grupo2.flysky.dto.requestDto.UserSaveDto;
import com.grupo2.flysky.service.UserService;
import com.grupo2.flysky.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {

    private IUserService service;

    public UsersController(UserService service){
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<?> saveUser(@RequestBody UserSaveDto userSave){
        return new ResponseEntity<>(service.saveUser(userSave),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id){
        return new ResponseEntity<>(service.findByIdentity(id), HttpStatus.OK);
    }


    @GetMapping("/all/")
    public ResponseEntity<?> findAllUsers(){
        return new ResponseEntity<>(service.findAllUsers(),HttpStatus.OK);
    }

}
