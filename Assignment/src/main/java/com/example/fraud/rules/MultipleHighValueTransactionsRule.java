package com.example.fraud.rules;

import com.example.fraud.model.FraudAlert;
import com.example.fraud.model.Transaction;
import com.example.fraud.storage.InMemoryTransactionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class MultipleHighValueTransactionsRule {
    private static final double HIGH_VALUE_THRESHOLD = 10000.0;
    private static final long TIME_WINDOW = 5; // 5 minutes

    @Autowired
    private InMemoryTransactionStorage transactionStorage;

    public FraudAlert apply(Transaction transaction) {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Transaction> transactions = transactionStorage.getTransactions();

        long count = transactions.stream()
                .filter(tx -> tx.getAmount() > HIGH_VALUE_THRESHOLD &&
                        Duration.between(tx.getTime(), currentTime).toMinutes() < TIME_WINDOW)
                .count();

        if (count > 1) {
            return new FraudAlert("Multiple High-Value Transactions", transaction);
        }
        return null;
    }
}
