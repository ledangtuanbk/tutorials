package com.example.springrediscache.service.impl;

import com.example.springrediscache.dto.UserCreateDTO;
import com.example.springrediscache.dto.UserResponseDTO;
import com.example.springrediscache.dto.UserUpdateDTO;
import com.example.springrediscache.entity.UserEntity;
import com.example.springrediscache.repository.UserRepository;
import com.example.springrediscache.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    @Cacheable(value = "all", cacheManager = "redisCacheManager")
    public List<UserResponseDTO> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserResponseDTO> returnedUsers = modelMapper.map(userEntities, new TypeToken<List<UserResponseDTO>>() {
        }.getType());
        return returnedUsers;
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public UserResponseDTO get( long id) throws CustomNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(id));
        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    @Override
    @CacheEvict(value = "users", key = "#id")
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO update(long id, UserUpdateDTO userUpdateDTO) throws CustomNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->  new CustomNotFoundException(id));
        userEntity.setName(userUpdateDTO.getName());
        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO create(UserCreateDTO userCreateDTO) {
        UserEntity userEntity = modelMapper.map(userCreateDTO, UserEntity.class);
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserResponseDTO.class);
    }
}
