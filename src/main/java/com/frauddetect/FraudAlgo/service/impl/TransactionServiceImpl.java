package com.frauddetect.FraudAlgo.service.impl;

import com.frauddetect.FraudAlgo.dto.TransactionDTO;
import com.frauddetect.FraudAlgo.entity.Transaction;
import com.frauddetect.FraudAlgo.exception.ResourceNotFoundException;
import com.frauddetect.FraudAlgo.mapper.TransactionMapper;
import com.frauddetect.FraudAlgo.repository.TransactionRepository;
import com.frauddetect.FraudAlgo.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper){
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDTO processTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transaction.setStatus("PENDING");
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDTO(savedTransaction);
    }

    @Override
    public TransactionDTO getTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        return transactionMapper.toDTO(transaction);
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO updateTransactionStatus(Long id, String status) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        transaction.setStatus(status);
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDTO(updatedTransaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        if(!transactionRepository.existsById(id)){
            throw new ResourceNotFoundException("Transaction not found with id: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
