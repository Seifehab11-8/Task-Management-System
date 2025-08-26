package com.orange.springtask.task_management_system.repository;

import com.orange.springtask.task_management_system.entities.Task;
import com.orange.springtask.task_management_system.entities.TaskStatus;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findTaskByTitleAndUser_Id(String title, Long userId);
    List<Task> findTasksByStatusAndUser_Id(TaskStatus status, Long userId);
    List<Task> findTasksByUser_Id(Long userId);
    List<Task> findTasksByStatusAndTitleAndUser_Id(TaskStatus status, String title, Long userId);
}
