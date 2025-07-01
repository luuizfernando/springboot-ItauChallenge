package com.projects.itau_challenge.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projects.itau_challenge.domain.transaction.Transaction;
import com.projects.itau_challenge.services.TransactionService;

@RestController
@RequestMapping(value = "transacao")
public class TransactionController {

    @Autowired
    TransactionService service;

    @PostMapping
    public ResponseEntity<Transaction> createTransation(@RequestBody Transaction transaction) {
        transaction = service.createTransaction(transaction);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(transaction.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}