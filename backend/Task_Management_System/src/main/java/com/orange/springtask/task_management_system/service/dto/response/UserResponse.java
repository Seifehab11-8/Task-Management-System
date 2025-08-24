package com.orange.springtask.task_management_system.service.dto.response;

import com.orange.springtask.task_management_system.service.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse extends UserDto {
    private final Long id;
}
