package com.example.fraud.rules;

import com.example.fraud.model.FraudAlert;
import com.example.fraud.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class HighValueTransactionRule {
    private static final double HIGH_VALUE_THRESHOLD = 10000.0;

    public FraudAlert apply(Transaction transaction) {
        if (transaction.getAmount() > HIGH_VALUE_THRESHOLD) {
            return new FraudAlert("High-Value Transaction", transaction);
        }
        return null;
    }
}
