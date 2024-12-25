package com.frauddetect.FraudAlgo.observer;

import com.frauddetect.FraudAlgo.entity.Transaction;

public interface FraudListener {
    void onFraudDetected(Transaction transaction);
}
