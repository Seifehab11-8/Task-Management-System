package com.orange.springtask.task_management_system.service.dto.mapper;

import com.orange.springtask.task_management_system.entities.Task;
import com.orange.springtask.task_management_system.entities.TaskStatus;
import com.orange.springtask.task_management_system.repository.UserRepository;
import com.orange.springtask.task_management_system.service.dto.request.TaskRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Data
@Component
public class TaskRequestMapper implements Function<TaskRequest, Task> {

    private final UserRepository userRepository;
    public TaskRequestMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Task apply(TaskRequest taskRequest) {
        return new Task(
                taskRequest.getTitle(),
                taskRequest.getDescription(),
                TaskStatus.valueOf(taskRequest.getStatus()),
                taskRequest.getDueDate()
        );
    }
}
