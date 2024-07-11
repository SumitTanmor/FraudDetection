package com.example.fraud.rules;

import com.example.fraud.model.FraudAlert;
import com.example.fraud.model.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DailyTransactionLimitRule {
    private static final int DAILY_TRANSACTION_LIMIT = 5;
    private LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
    private AtomicInteger dailyTransactionCount = new AtomicInteger(0);

    public FraudAlert apply(Transaction transaction) {
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(startOfDay.plusDays(1))) {
            startOfDay = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
            dailyTransactionCount.set(0);
        }

        dailyTransactionCount.incrementAndGet();

        if (dailyTransactionCount.get() > DAILY_TRANSACTION_LIMIT) {
            return new FraudAlert("Daily Transaction Limit Exceeded", transaction);
        }
        return null;
    }
}

