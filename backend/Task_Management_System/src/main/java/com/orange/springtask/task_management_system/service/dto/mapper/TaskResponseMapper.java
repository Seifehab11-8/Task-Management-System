package com.orange.springtask.task_management_system.service.dto.mapper;

import com.orange.springtask.task_management_system.entities.Task;
import com.orange.springtask.task_management_system.service.dto.response.TaskResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Data
@Component
public class TaskResponseMapper implements Function<Task, TaskResponse> {
    @Override
    public TaskResponse apply(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().toString(),
                task.getDueDate()
        );
    }
}
