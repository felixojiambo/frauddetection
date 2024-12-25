package com.frauddetect.FraudAlgo.strategy;
import com.frauddetect.FraudAlgo.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FraudDetectionContext {

    private final List<FraudDetectionStrategy> strategies;

    @Autowired
    public FraudDetectionContext(List<FraudDetectionStrategy> strategies){
        this.strategies = strategies;
    }

    public boolean detectFraud(Transaction transaction){
        for(FraudDetectionStrategy strategy : strategies){
            if(strategy.isFraudulent(transaction)){
                return true;
            }
        }
        return false;
    }
}
