package com.example.springrediscache.service;

import com.example.springrediscache.dto.UserCreateDTO;
import com.example.springrediscache.dto.UserResponseDTO;
import com.example.springrediscache.dto.UserUpdateDTO;
import com.example.springrediscache.service.impl.CustomNotFoundException;

import java.util.List;


public interface UserService {
    List<UserResponseDTO> getAll();

    UserResponseDTO get(long id) throws CustomNotFoundException;

    void delete(long id);

    UserResponseDTO update(long id, UserUpdateDTO userUpdateDTO) throws CustomNotFoundException;

    UserResponseDTO create(UserCreateDTO userCreateDTO);
}
