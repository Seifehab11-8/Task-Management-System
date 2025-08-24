package com.orange.springtask.task_management_system.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.springtask.task_management_system.entities.User;
import com.orange.springtask.task_management_system.repository.UserRepository;
import com.orange.springtask.task_management_system.service.dto.request.UserRequest;
import com.orange.springtask.task_management_system.service.dto.response.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final ObjectMapper mapper;

    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapper = mapper;
    }

    public UserResponse acceptRegisterRequest(UserRequest userRequest) throws SQLIntegrityConstraintViolationException {
        userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        User user = userRepository.save(mapper.convertValue(userRequest, User.class));
        return mapper.convertValue(user, UserResponse.class);
    }


}
