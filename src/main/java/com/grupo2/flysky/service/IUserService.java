package com.grupo2.flysky.service;

import com.grupo2.flysky.dto.responseDto.ResponseDto;
import com.grupo2.flysky.dto.responseDto.ResponseUserDto;
import com.grupo2.flysky.dto.requestDto.UserSaveDto;

import java.util.List;

public interface IUserService {

    ResponseDto saveUser(UserSaveDto user);

    List<ResponseUserDto> findAllUsers();

    ResponseUserDto findByIdentity(Long id);
}
