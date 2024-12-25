package com.frauddetect.FraudAlgo.observer;
import com.frauddetect.FraudAlgo.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class SMSNotificationListener implements FraudListener {

    @Override
    public void onFraudDetected(Transaction transaction) {
        // Simulate sending an SMS notification
        System.out.println("SMS Notification: Transaction ID " + transaction.getId() + " flagged as fraudulent.");
    }
}
