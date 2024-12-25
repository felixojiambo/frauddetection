package com.frauddetect.FraudAlgo.strategy;
import com.frauddetect.FraudAlgo.entity.Transaction;
import com.frauddetect.FraudAlgo.service.DecisionTreeModelService;
import com.github.haifengl.smile.classification.DecisionTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DecisionTreeFraudDetectionStrategy implements FraudDetectionStrategy {

    private final DecisionTree model;

    @Autowired
    public DecisionTreeFraudDetectionStrategy(DecisionTreeModelService modelService){
        this.model = modelService.getDecisionTreeModel();
    }

    @Override
    public boolean isFraudulent(Transaction transaction) {
        // Extract features in the same order as used during training
        double[] features = extractFeatures(transaction);
        int prediction = model.predict(features);
        return prediction == 1; // Assuming 1 indicates fraud
    }

    private double[] extractFeatures(Transaction transaction){
        // Implement feature extraction based on your training data
        // Example:
        // Feature 1: Transaction amount
        // Feature 2: Transaction type encoded as numerical value
        // Feature 3: Time of transaction (hour)
        // Ensure consistency with training

        double amount = transaction.getAmount().doubleValue();
        double type = encodeTransactionType(transaction.getType());
        double time = transaction.getTimestamp().getHour();

        return new double[]{amount, type, time};
    }

    private double encodeTransactionType(String type){
        // Simple encoding: DEPOSIT=0, WITHDRAWAL=1
        switch(type.toUpperCase()){
            case "DEPOSIT":
                return 0;
            case "WITHDRAWAL":
                return 1;
            default:
                return -1; // Unknown type
        }
    }
}
