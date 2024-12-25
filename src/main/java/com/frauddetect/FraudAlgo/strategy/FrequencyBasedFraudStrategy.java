package com.frauddetect.FraudAlgo.strategy;

import com.frauddetect.FraudAlgo.entity.Transaction;
import com.frauddetect.FraudAlgo.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FrequencyBasedFraudStrategy implements FraudDetectionStrategy {

    private static final int TRANSACTION_LIMIT = 5;
    private static final int TIME_FRAME_HOURS = 1;

    private final TransactionRepository transactionRepository;

    // Constructor injection
    public FrequencyBasedFraudStrategy(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public boolean isFraudulent(Transaction transaction) {
        LocalDateTime startTime = transaction.getTimestamp().minusHours(TIME_FRAME_HOURS);
        long recentTransactions = transactionRepository.countByUserIdAndTimestampAfter(transaction.getUser().getId(), startTime);
        return recentTransactions > TRANSACTION_LIMIT;
    }
}
