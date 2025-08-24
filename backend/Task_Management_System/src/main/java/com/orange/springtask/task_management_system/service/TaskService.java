package com.orange.springtask.task_management_system.service;

import com.orange.springtask.task_management_system.entities.Task;
import com.orange.springtask.task_management_system.entities.User;
import com.orange.springtask.task_management_system.repository.TaskRepository;
import com.orange.springtask.task_management_system.repository.UserRepository;
import com.orange.springtask.task_management_system.service.dto.mapper.TaskRequestMapper;
import com.orange.springtask.task_management_system.service.dto.mapper.TaskRequestUpdateMapper;
import com.orange.springtask.task_management_system.service.dto.mapper.TaskResponseMapper;
import com.orange.springtask.task_management_system.service.dto.principal.UserPrincipal;
import com.orange.springtask.task_management_system.service.dto.request.TaskRequest;
import com.orange.springtask.task_management_system.service.dto.request.TaskRequestUpdate;
import com.orange.springtask.task_management_system.service.dto.response.TaskResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private TaskRequestMapper taskRequestMapper;
    private TaskResponseMapper taskResponseMapper;
    private TaskRequestUpdateMapper taskRequestUpdateMapper;

    public TaskResponse getTaskByTitle(String title) throws ObjectNotFoundException{
        User user = getCurrentUser();
        if(user == null) throw new ObjectNotFoundException(new Object(), "not found");
        Optional<Task> task = taskRepository.findTaskByTitleAndUser_Id(title, user.getId());
        return taskResponseMapper.apply(task.orElseThrow(()->new ObjectNotFoundException(task ,"task of title: "
                + title + "and user_id: "
                + user.getId() + "not found")));
    }

    public List<TaskResponse> getAllTasks(String status) throws ObjectNotFoundException{
        Optional<List<Task>> taskList;
        User user = getCurrentUser();
        if(user == null) throw new ObjectNotFoundException(new Object(), "not found");
        if(status != null)
            taskList = taskRepository.findTasksByStatusAndUser_Id(status, user.getId());
        else
            taskList = taskRepository.findTasksByUser_Id(user.getId());

        if(taskList.isEmpty()) {
            throw new ObjectNotFoundException(taskList ,"tasks with status: "
                    + status + "and user_id: "
                    + user.getId() + "not found");
        }
        return taskList.get().stream()
                .map(task ->taskResponseMapper.apply(task))
                .collect(Collectors.toList());
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        User user = getCurrentUser();
        if(user == null) throw new ObjectNotFoundException(new Object(), "not found");
        Task taskBeforeFK = taskRequestMapper.apply(taskRequest);
        taskBeforeFK.setUser(user);
        Task task = taskRepository.save(taskBeforeFK);
        return taskResponseMapper.apply(task);
    }

    public TaskResponse updateTask(TaskRequestUpdate taskRequestUpdate) {
        User user;
        user = getCurrentUser();
        if(user == null) throw new ObjectNotFoundException(new Object(), "not found");
        Task taskBeforeFK = taskRequestUpdateMapper.apply(taskRequestUpdate);
        taskBeforeFK.setUser(user);
        Task task = taskRepository.save(taskBeforeFK);
        return taskResponseMapper.apply(task);
    }


    public void deleteTaskById(Long id) throws ObjectNotFoundException {
        try{
            taskRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException ignored) {
            throw new ObjectNotFoundException(id, "task with id: " + id + " is not found");
        }
    }

    public User getCurrentUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepository.findUserByUsername(userPrincipal.getUsername());
    }

}
