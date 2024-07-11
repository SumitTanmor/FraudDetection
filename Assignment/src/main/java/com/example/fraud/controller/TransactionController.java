package com.example.fraud.controller;

import com.example.fraud.model.FraudAlert;
import com.example.fraud.model.Transaction;
import com.example.fraud.service.FraudDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private FraudDetectionService fraudDetectionService;

    @PostMapping
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction) {
        List<FraudAlert> alerts = fraudDetectionService.checkTransaction(transaction);
        if (!alerts.isEmpty()) {
            return ResponseEntity.status(400).body(alerts);
        }
        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(fraudDetectionService.getAllTransactions());
    }
}
