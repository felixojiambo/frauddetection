package com.frauddetect.FraudAlgo.service;
import com.frauddetect.FraudAlgo.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO getUser(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
