package com.orange.springtask.task_management_system.repository;

import com.orange.springtask.task_management_system.entities.Task;
import com.orange.springtask.task_management_system.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Optional<Task> findTaskByTitleAndUser_Id(String title, Long userId);
    Optional<List<Task>> findTasksByStatusAndUser_Id(String status, Long userId);
    Optional<List<Task>> findTasksByUser_Id(Long userId);
}
