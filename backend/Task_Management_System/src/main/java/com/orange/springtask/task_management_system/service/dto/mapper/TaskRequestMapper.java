package com.orange.springtask.task_management_system.service.dto.mapper;

import com.orange.springtask.task_management_system.entities.Task;
import com.orange.springtask.task_management_system.entities.User;
import com.orange.springtask.task_management_system.repository.UserRepository;
import com.orange.springtask.task_management_system.service.dto.request.TaskRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.ObjectNotFoundException;
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
                taskRequest.getStatus(),
                taskRequest.getDueDate()
        );
    }
}
