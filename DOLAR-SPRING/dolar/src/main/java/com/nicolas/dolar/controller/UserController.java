package com.nicolas.dolar.controller;

import com.nicolas.dolar.dtos.user.NewUserDTO;
import com.nicolas.dolar.entities.UserEntity;
import com.nicolas.dolar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUsers")
    public List<UserEntity> getUsers() {
        return userService.listUsers();
    }

    @GetMapping("/getUserById/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/register")
    public UserEntity register(@RequestBody NewUserDTO user) {
        return userService.saveUser(user);
    }

}
