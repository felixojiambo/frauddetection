package com.frauddetect.FraudAlgo.strategy;

import com.frauddetect.FraudAlgo.entity.Transaction;

public interface FraudDetectionStrategy {
    boolean isFraudulent(Transaction transaction);
}
