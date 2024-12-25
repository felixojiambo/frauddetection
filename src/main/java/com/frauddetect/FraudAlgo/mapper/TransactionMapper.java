package com.frauddetect.FraudAlgo.mapper;

import com.frauddetect.FraudAlgo.dto.TransactionDTO;
import com.frauddetect.FraudAlgo.entity.Transaction;
import com.frauddetect.FraudAlgo.entity.User;
import com.frauddetect.FraudAlgo.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TransactionMapper {

    @Autowired
    private UserRepository userRepository;

    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUserById")
    public abstract Transaction toEntity(TransactionDTO transactionDTO);

    @Mapping(source = "user.id", target = "userId")
    public abstract TransactionDTO toDTO(Transaction transaction);

    @Named("mapUserById")
    protected User mapUserById(Long userId) {
        if (userId == null) {
            return null;
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
}
