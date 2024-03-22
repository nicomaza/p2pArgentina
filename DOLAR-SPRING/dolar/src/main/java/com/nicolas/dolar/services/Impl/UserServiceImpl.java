package com.nicolas.dolar.services.Impl;

import com.nicolas.dolar.dtos.user.NewUserDTO;
import com.nicolas.dolar.entities.UserEntity;
import com.nicolas.dolar.repository.userJpaRepository;
import com.nicolas.dolar.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    userJpaRepository userJPARepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserEntity> listUsers() {
        return userJPARepository.findAll();
    }

    @Override
    public UserEntity getUser(Long id) {
        return userJPARepository.findById(id).orElseThrow();
    }

    @Override
    public UserEntity saveUser(NewUserDTO user) {

        UserEntity newUserEntity = modelMapper.map(user, UserEntity.class);

        newUserEntity.setVerified(Boolean.FALSE);
        newUserEntity.setLastlog(LocalDateTime.now());

        return userJPARepository.save(newUserEntity);
    }

}
