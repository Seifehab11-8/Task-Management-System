package com.orange.springtask.task_management_system.repository;

import com.orange.springtask.task_management_system.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
