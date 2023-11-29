package com.grupo2.flysky.service;

import com.grupo2.flysky.dto.ResponseDto;
import com.grupo2.flysky.dto.ResponseUserDto;
import com.grupo2.flysky.dto.UserSaveDto;

import java.util.List;

public interface UserServiceInterface {

    ResponseDto saveUser(UserSaveDto user);

    List<ResponseUserDto> findAllUsers();

    ResponseUserDto findByIdentity(Long id);
}
