package com.projects.itau_challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.itau_challenge.domain.user.User;
import com.projects.itau_challenge.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    public User registerUser(@RequestBody User u) {
        return service.registerUser(u);
    }
}