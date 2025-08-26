package com.orange.springtask.task_management_system.service;

import com.orange.springtask.task_management_system.entities.Task;
import com.orange.springtask.task_management_system.entities.TaskStatus;
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
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private TaskRequestMapper taskRequestMapper;
    private TaskResponseMapper taskResponseMapper;
    private TaskRequestUpdateMapper taskRequestUpdateMapper;


    public List<TaskResponse> getAllTasks(String status, String title) throws ObjectNotFoundException{
        List<Task> taskList;
        User user = getCurrentUser();
        if(user == null) throw new ObjectNotFoundException(new Object(), "not found");
        if(status != null && title == null)
            taskList = taskRepository.findTasksByStatusAndUser_Id(TaskStatus.valueOf(status), user.getId());
        else if(title != null && status == null)
            taskList = taskRepository.findTaskByTitleAndUser_Id(title, user.getId());
        else if(title != null && status != null)
            taskList = taskRepository.findTasksByStatusAndTitleAndUser_Id(TaskStatus.valueOf(status), title, user.getId());
        else
            taskList = taskRepository.findTasksByUser_Id(user.getId());
            
        return taskList.stream()
                .map(task ->taskResponseMapper.apply(task))
                .collect(Collectors.toList());
    }

    public TaskResponse getTaskById(Long task_id) {
        User user = getCurrentUser();
        if(user == null) throw new ObjectNotFoundException(new Object(), "not found");
        return taskResponseMapper.apply(taskRepository.findById(task_id).orElse(null));
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        User user = getCurrentUser();
        if(user == null) throw new ObjectNotFoundException(new Object(), "not found");
        Task taskBeforeFK = taskRequestMapper.apply(taskRequest);
        taskBeforeFK.setUser(user);
        Task task = taskRepository.save(taskBeforeFK);
        return taskResponseMapper.apply(task);
    }

    public TaskResponse updateTask(Long task_id,TaskRequestUpdate taskRequestUpdate) {
        User user;
        user = getCurrentUser();
        if(user == null) throw new ObjectNotFoundException(new Object(), "not found");
        Task task = taskRequestUpdateMapper.apply(taskRequestUpdate);
        task.setUser(user);
        task = taskRepository.save(task);
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
