package com.example.fraud.rules;

import com.example.fraud.model.FraudAlert;
import com.example.fraud.model.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MonthlyTransactionLimitRule {
    private static final int MONTHLY_TRANSACTION_LIMIT = 50;
    private LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
    private AtomicInteger monthlyTransactionCount = new AtomicInteger(0);

    public FraudAlert apply(Transaction transaction) {
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(startOfMonth.plusMonths(1))) {
            startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            monthlyTransactionCount.set(0);
        }

        monthlyTransactionCount.incrementAndGet();

        if (monthlyTransactionCount.get() > MONTHLY_TRANSACTION_LIMIT) {
            return new FraudAlert("Monthly Transaction Limit Exceeded", transaction);
        }
        return null;
    }
}
