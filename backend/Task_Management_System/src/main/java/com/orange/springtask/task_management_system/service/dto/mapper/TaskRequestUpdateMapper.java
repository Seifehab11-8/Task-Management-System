package com.orange.springtask.task_management_system.service.dto.mapper;

import com.orange.springtask.task_management_system.entities.Task;
import com.orange.springtask.task_management_system.entities.TaskStatus;
import com.orange.springtask.task_management_system.repository.UserRepository;
import com.orange.springtask.task_management_system.service.dto.request.TaskRequestUpdate;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Data
@Component
public class TaskRequestUpdateMapper implements Function<TaskRequestUpdate, Task> {

    private final UserRepository userRepository;

    public TaskRequestUpdateMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Task apply(TaskRequestUpdate taskRequestUpdate) {
        return new Task(
                taskRequestUpdate.getId(),
                taskRequestUpdate.getTitle(),
                taskRequestUpdate.getDescription(),
                TaskStatus.valueOf(taskRequestUpdate.getStatus()),
                taskRequestUpdate.getDueDate()
        );
    }
}
