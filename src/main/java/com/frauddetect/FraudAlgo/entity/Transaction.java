package com.frauddetect.FraudAlgo.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // e.g., DEPOSIT, WITHDRAWAL
    private BigDecimal amount;
    private LocalDateTime timestamp;

    // Many-to-One relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String status; // e.g., PENDING, COMPLETED, FLAGGED
}
