package com.example.springrediscache.controller;

import com.example.springrediscache.dto.UserCreateDTO;
import com.example.springrediscache.dto.UserResponseDTO;
import com.example.springrediscache.dto.UserUpdateDTO;
import com.example.springrediscache.service.UserService;
import com.example.springrediscache.service.impl.CustomNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDTO> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public UserResponseDTO create(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.create(userCreateDTO);
    }

    @GetMapping("/{id}")
    public UserResponseDTO get(@PathVariable("id") long id) throws CustomNotFoundException {
        return userService.get(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable("id") long id, UserUpdateDTO userUpdateDTO) throws CustomNotFoundException {
        return userService.update(id, userUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        userService.delete(id);
    }
}
