package com.example.fraud.service;

import com.example.fraud.model.FraudAlert;
import com.example.fraud.model.Transaction;
import com.example.fraud.rules.*;
import com.example.fraud.storage.InMemoryTransactionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FraudDetectionService {

    @Autowired
    private InMemoryTransactionStorage transactionStorage;

    @Autowired
    private HighValueTransactionRule highValueTransactionRule;

    @Autowired
    private MultipleHighValueTransactionsRule multipleHighValueTransactionsRule;

    @Autowired
    private DailyTransactionLimitRule dailyTransactionLimitRule;

    @Autowired
    private MonthlyTransactionLimitRule monthlyTransactionLimitRule;

    public List<FraudAlert> checkTransaction(Transaction transaction) {
        List<FraudAlert> alerts = new ArrayList<>();

        transactionStorage.addTransaction(transaction);

        FraudAlert alert = highValueTransactionRule.apply(transaction);
        if (alert != null) {
            alerts.add(alert);
        }

        alert = multipleHighValueTransactionsRule.apply(transaction);
        if (alert != null) {
            alerts.add(alert);
        }

        alert = dailyTransactionLimitRule.apply(transaction);
        if (alert != null) {
            alerts.add(alert);
        }

        alert = monthlyTransactionLimitRule.apply(transaction);
        if (alert != null) {
            alerts.add(alert);
        }

        return alerts;
    }

    public List<Transaction> getAllTransactions() {
        return transactionStorage.getTransactions();
    }
}

