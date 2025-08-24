package com.orange.springtask.task_management_system.controller;

import com.orange.springtask.task_management_system.service.UserService;
import com.orange.springtask.task_management_system.service.dto.request.UserRequest;
import com.orange.springtask.task_management_system.service.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@Tag(name = "User Management", description = "Endpoints for user registration and management")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register User", description = "Register a new user in the system.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request - user already exists or invalid data")
    })
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        try {
            return new ResponseEntity<>(userService.acceptRegisterRequest(userRequest),
                    HttpStatus.CREATED);
        } catch (SQLIntegrityConstraintViolationException e) {
            return new ResponseEntity<>((HttpStatus.BAD_REQUEST));
        }
    }
}
