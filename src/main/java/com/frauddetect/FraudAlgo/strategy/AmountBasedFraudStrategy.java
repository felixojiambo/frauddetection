package com.frauddetect.FraudAlgo.strategy;
import com.frauddetect.FraudAlgo.entity.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AmountBasedFraudStrategy implements FraudDetectionStrategy {

    private static final BigDecimal FRAUD_AMOUNT_THRESHOLD = new BigDecimal("10000");

    @Override
    public boolean isFraudulent(Transaction transaction) {
        return transaction.getAmount().compareTo(FRAUD_AMOUNT_THRESHOLD) > 0;
    }
}
