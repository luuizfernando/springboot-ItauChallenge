package com.projects.itau_challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.itau_challenge.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}