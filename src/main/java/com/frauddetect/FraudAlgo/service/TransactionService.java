package com.frauddetect.FraudAlgo.service;
import com.frauddetect.FraudAlgo.dto.TransactionDTO;
import java.util.List;
public interface TransactionService {
    TransactionDTO processTransaction(TransactionDTO transactionDTO);
    TransactionDTO getTransaction(Long id);
    List<TransactionDTO> getAllTransactions();
    TransactionDTO updateTransactionStatus(Long id, String status);
    void deleteTransaction(Long id);
}
