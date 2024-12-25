package com.frauddetect.FraudAlgo.strategy;
import com.frauddetect.FraudAlgo.entity.Transaction;
import com.frauddetect.FraudAlgo.service.KMeansModelService;
import com.github.haifengl.smile.clustering.KMeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KMeansFraudDetectionStrategy implements FraudDetectionStrategy {

    private final KMeans kmeans;

    @Autowired
    public KMeansFraudDetectionStrategy(KMeansModelService modelService){
        this.kmeans = modelService.getKMeansModel();
    }

    @Override
    public boolean isFraudulent(Transaction transaction) {
        double[] features = extractFeatures(transaction);
        int cluster = kmeans.predict(features);
        // Assuming cluster 1 is fraudulent
        return cluster == 1;
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
