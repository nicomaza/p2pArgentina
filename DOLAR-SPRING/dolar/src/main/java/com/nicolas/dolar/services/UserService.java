package com.nicolas.dolar.services;

import com.nicolas.dolar.dtos.user.NewUserDTO;
import com.nicolas.dolar.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserEntity> listUsers();

    UserEntity getUser(Long id);

    UserEntity saveUser(NewUserDTO user);
}
