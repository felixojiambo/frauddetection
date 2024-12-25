package com.frauddetect.FraudAlgo.aspect;
import com.frauddetect.FraudAlgo.dto.TransactionDTO;
import com.frauddetect.FraudAlgo.entity.Transaction;
import com.frauddetect.FraudAlgo.mapper.TransactionMapper;
import com.frauddetect.FraudAlgo.observer.FraudListener;
import com.frauddetect.FraudAlgo.service.TransactionService;
import com.frauddetect.FraudAlgo.strategy.FraudDetectionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Aspect
@Component
public class FraudDetectionAspect {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;
    private final FraudDetectionContext fraudDetectionContext;
    private final List<FraudListener> fraudListeners;

    // Constructor injection for dependencies
    public FraudDetectionAspect(
            TransactionService transactionService,
            TransactionMapper transactionMapper, // Inject TransactionMapper
            FraudDetectionContext fraudDetectionContext,
            List<FraudListener> fraudListeners
    ) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
        this.fraudDetectionContext = fraudDetectionContext;
        this.fraudListeners = fraudListeners;
    }

    // Pointcut for processing transactions
    @Pointcut("execution(* com.frauddetect.FraudAlgo.service.TransactionService.processTransaction(..))")
    public void transactionProcessing() {}

    // After Returning advice to analyze transaction
    @AfterReturning(pointcut = "transactionProcessing()", returning = "transactionDTO")
    public void analyzeTransaction(JoinPoint joinPoint, TransactionDTO transactionDTO) {
        // Fetch the Transaction entity using the mapper
        Transaction transaction = transactionMapper.toEntity(transactionDTO);

        boolean isFraud = fraudDetectionContext.detectFraud(transaction);
        if (isFraud) {
            transaction.setStatus("FLAGGED");
            transactionService.updateTransactionStatus(transaction.getId(), "FLAGGED");
            System.out.println("Fraud detected: Transaction ID " + transaction.getId() + " flagged for amount " + transaction.getAmount());

            // Notify listeners
            fraudListeners.forEach(listener -> listener.onFraudDetected(transaction));
        } else {
            transaction.setStatus("COMPLETED");
            transactionService.updateTransactionStatus(transaction.getId(), "COMPLETED");
            System.out.println("Transaction ID " + transaction.getId() + " completed successfully");
        }
    }
}
