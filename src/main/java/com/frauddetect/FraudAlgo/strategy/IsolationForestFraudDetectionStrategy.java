package com.frauddetect.FraudAlgo.strategy;
import com.frauddetect.FraudAlgo.entity.Transaction;
import com.frauddetect.FraudAlgo.service.IsolationForestModelService;
import com.github.haifengl.smile.anomaly.IsolationForest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsolationForestFraudDetectionStrategy implements FraudDetectionStrategy {

    private final IsolationForest isolationForest;

    @Autowired
    public IsolationForestFraudDetectionStrategy(IsolationForestModelService modelService){
        this.isolationForest = modelService.getIsolationForestModel();
    }

    @Override
    public boolean isFraudulent(Transaction transaction) {
        double[] features = extractFeatures(transaction);
        double score = isolationForest.score(features);
        // Define a threshold; transactions with scores below are considered anomalies
        return score < -0.5;
    }

    private double[] extractFeatures(Transaction transaction){
        double amount = transaction.getAmount().doubleValue();
        double type = encodeTransactionType(transaction.getType());
        double time = transaction.getTimestamp().getHour();
        return new double[]{amount, type, time};
    }

    private double encodeTransactionType(String type){
        switch(type.toUpperCase()){
            case "DEPOSIT":
                return 0;
            case "WITHDRAWAL":
                return 1;
            default:
                return -1;
        }
    }
}
