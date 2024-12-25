package com.frauddetect.FraudAlgo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private String type;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private Long userId;
    private String status;
}
