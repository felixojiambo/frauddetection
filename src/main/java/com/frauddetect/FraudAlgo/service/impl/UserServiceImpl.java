package com.frauddetect.FraudAlgo.service.impl;
import com.frauddetect.FraudAlgo.dto.UserDTO;
import com.frauddetect.FraudAlgo.entity.User;
import com.frauddetect.FraudAlgo.exception.ResourceNotFoundException;
import com.frauddetect.FraudAlgo.mapper.UserMapper;
import com.frauddetect.FraudAlgo.repository.UserRepository;
import com.frauddetect.FraudAlgo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setRole("USER"); // Default role
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
