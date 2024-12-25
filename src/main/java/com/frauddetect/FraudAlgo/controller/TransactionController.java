package com.frauddetect.FraudAlgo.controller;

import com.frauddetect.FraudAlgo.dto.TransactionDTO;
import com.frauddetect.FraudAlgo.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    // Constructor injection
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> processTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO processedTransaction = transactionService.processTransaction(transactionDTO);
        return ResponseEntity.ok(processedTransaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long id) {
        TransactionDTO transaction = transactionService.getTransaction(id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TransactionDTO> updateTransactionStatus(@PathVariable Long id, @RequestParam String status) {
        TransactionDTO updatedTransaction = transactionService.updateTransactionStatus(id, status);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
