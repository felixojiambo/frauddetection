package com.frauddetect.FraudAlgo.mapper;

import com.frauddetect.FraudAlgo.dto.TransactionDTO;
import com.frauddetect.FraudAlgo.entity.Transaction;
import com.frauddetect.FraudAlgo.entity.User;
import com.frauddetect.FraudAlgo.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class TransactionMapper {

    private final UserRepository userRepository;

    // Constructor injection for better design and testability
    public TransactionMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Mapping(source = "userId", target = "user")
    public abstract Transaction toEntity(TransactionDTO transactionDTO);

    @Mapping(source = "user.id", target = "userId")
    public abstract TransactionDTO toDTO(Transaction transaction);

    @AfterMapping
    protected void afterMapping(@MappingTarget Transaction transaction, TransactionDTO transactionDTO) {
        if (transactionDTO.getUserId() != null) {
            User user = userRepository.findById(transactionDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + transactionDTO.getUserId()));
            transaction.setUser(user);
        }
    }
}
