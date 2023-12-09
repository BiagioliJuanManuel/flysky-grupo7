package com.grupo2.flysky.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grupo2.flysky.dto.responseDto.ResponseDto;
import com.grupo2.flysky.dto.responseDto.ResponseUserDto;
import com.grupo2.flysky.dto.requestDto.UserSaveDto;
import com.grupo2.flysky.entity.User;
import com.grupo2.flysky.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private IUserRepository repository;

    private final ObjectMapper mapper;

    public UserService(IUserRepository repository){
        this.repository = repository;
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public ResponseDto saveUser(UserSaveDto user) {
        User userEntity = mapper.convertValue(user, User.class);
        User responseRepo = repository.save(userEntity);

        return new ResponseDto("el usuario "+ responseRepo.getUserName() + " se creo correctamente");
    }

    @Override
    public List<ResponseUserDto> findAllUsers() {
        return repository.findAll().stream().map(user-> new ResponseUserDto(user.getUserName())).toList();
    }


    @Override
    public ResponseUserDto findByIdentity(Long id) {
        return new ResponseUserDto(repository.findById(id).get().getUserName());
    }
}
