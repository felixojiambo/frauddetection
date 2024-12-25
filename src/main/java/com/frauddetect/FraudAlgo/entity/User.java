package com.frauddetect.FraudAlgo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String role; // e.g., USER, ADMIN

    // One-to-Many relationship with Transaction
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;
}
