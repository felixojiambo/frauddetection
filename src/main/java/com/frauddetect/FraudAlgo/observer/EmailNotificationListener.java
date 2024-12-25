package com.frauddetect.FraudAlgo.observer;
import com.frauddetect.FraudAlgo.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationListener implements FraudListener {

    @Override
    public void onFraudDetected(Transaction transaction) {
        // Simulate sending an email notification
        System.out.println("Email Notification: Transaction ID " + transaction.getId() + " flagged as fraudulent.");
    }
}
