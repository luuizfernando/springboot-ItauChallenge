package com.projects.itau_challenge.services;

import java.time.Instant;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.projects.itau_challenge.domain.transaction.Transaction;
import com.projects.itau_challenge.domain.transaction.TransactionStatistics;
import com.projects.itau_challenge.repositories.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository repository;

    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public Transaction createTransaction(Transaction t) {
        validateTransactionValue(t);
        validateTransactionDate(t);
        try {
            verifyFraud(t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON when checking fraud.", e);
        }
        return repository.save(t);
    }

    public void deleteAllTransactions() {
        repository.deleteAll();
    }

    public TransactionStatistics getTransactionStatistics(List<Transaction> transactions) {
        Instant last60Seconds = Instant.now().minusSeconds(60);
        List<Transaction> transactionsInLast60Seconds = transactions.stream()
            .filter(t -> t.getDataHora().isAfter(last60Seconds))
            .collect(Collectors.toList());

        if (transactionsInLast60Seconds.isEmpty()) {
            return new TransactionStatistics(0L, 0.0, 0.0, 0.0, 0.0);
        } else {
            DoubleSummaryStatistics stats = transactionsInLast60Seconds.stream()
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();

            return new TransactionStatistics(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
            );
        }
    }

    private void validateTransactionValue(Transaction t) {
        if (t.getValor() < 0) {
            throw new IllegalArgumentException("Transaction value cannot be negative.");
        }
    }

    private void validateTransactionDate(Transaction t) {
        Instant now = Instant.now();
        if (t.getDataHora().isAfter(now)) {
            throw new IllegalArgumentException("Transaction date cannot be in the future.");
        }
    }

    private String verifyFraud(Transaction t) throws JsonProcessingException {
        String url = "http://localhost:8000/predict/"; 

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> body = new HashMap<>();
        body.put("valor", t.getValor());
        
        body.put("dataHora", t.getDataHora().toString());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(body);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        try {
            ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Map<String, String>>() {}
            );
            
            if (response.getBody() != null && response.getBody().containsKey("Result")) {
                return response.getBody().get("Result");
            } else {
                System.out.println("Fraud template response does not contain 'Result' or is empty.");
                return "indeterminate";
            }
        } catch (HttpClientErrorException e) {
            System.out.println("HTTP error when querying fraud model (" + e.getStatusCode() + "): " + e.getResponseBodyAsString());
            return "http_error";
        } catch (Exception e) {
            System.out.println("Unexpected error while querying fraud model: " + e.getMessage());
            return "general_error";
        }
    }
}