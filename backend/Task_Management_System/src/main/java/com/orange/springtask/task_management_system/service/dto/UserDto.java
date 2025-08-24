package com.orange.springtask.task_management_system.service.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String password;
    private int age;
}
