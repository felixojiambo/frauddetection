package com.frauddetect.FraudAlgo.mapper;

import com.frauddetect.FraudAlgo.dto.UserDTO;
import com.frauddetect.FraudAlgo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
