package com.projects.itau_challenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.itau_challenge.domain.user.User;
import com.projects.itau_challenge.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository repository;

    public User registerUser(User u) {
        return repository.save(u);
    }
    
}