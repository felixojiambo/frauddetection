package com.frauddetect.FraudAlgo.repository;
import com.frauddetect.FraudAlgo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    long countByUserIdAndTimestampAfter(Long userId, LocalDateTime timestamp);
}
