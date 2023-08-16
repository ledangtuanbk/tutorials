package com.example.springrediscache.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO implements Serializable {
    private Long id;
    private String name;
}
