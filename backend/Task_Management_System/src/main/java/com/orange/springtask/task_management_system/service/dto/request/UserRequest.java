package com.orange.springtask.task_management_system.service.dto.request;
import com.orange.springtask.task_management_system.service.dto.UserDto;

public class UserRequest extends UserDto {

    public UserRequest(String username, String email, String password, int age) {
        super(username, email, password, age);
    }
}
