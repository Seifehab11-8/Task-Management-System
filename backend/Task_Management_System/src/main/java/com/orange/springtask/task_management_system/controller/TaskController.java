package com.orange.springtask.task_management_system.controller;

import com.orange.springtask.task_management_system.service.TaskService;
import com.orange.springtask.task_management_system.service.dto.request.TaskRequest;
import com.orange.springtask.task_management_system.service.dto.request.TaskRequestUpdate;
import com.orange.springtask.task_management_system.service.dto.response.TaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Task Management", description = "Endpoints for managing tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/")
    @Operation(summary = "Greeting", description = "Returns a welcome message.")
    public ResponseEntity<String> greeting() {
        return new ResponseEntity<>("Welcome to Task Management System", HttpStatus.OK);
    }

    @GetMapping("/tasks")
    @Operation(summary = "Get Tasks", description = "Retrieve all tasks, optionally filtered by status.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Tasks not found")
    })
    public ResponseEntity<List<TaskResponse>> getTasksByUser_IdOrStatus(
            @Parameter(description = "Status to filter tasks", required = false)
            @RequestParam(name = "status", required = false) String status) {
        try{
            return new ResponseEntity<>(
                    taskService.getAllTasks(status),
                    HttpStatus.OK
            );
        }
        catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/task")
    @Operation(summary = "Get Task by Title", description = "Retrieve a task by its title.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Task retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResponse> getTaskByUser_IdAndTitle(
            @Parameter(description = "Title of the task", required = true)
            @RequestParam(name = "title") String title) {
        try{
            return new ResponseEntity<>(
                    taskService.getTaskByTitle(title),
                    HttpStatus.OK
            );
        }
        catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/task")
    @Operation(summary = "Add Task", description = "Create a new task.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Task created successfully"),
        @ApiResponse(responseCode = "404", description = "Related resource not found")
    })
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest taskRequest) {
        try{
            return new ResponseEntity<>(taskService.createTask(taskRequest)
                    , HttpStatus.CREATED);
        }
        catch(ObjectNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/task")
    @Operation(summary = "Update Task", description = "Update an existing task.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Task updated successfully"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResponse> updateTask(@RequestBody TaskRequestUpdate taskRequestUpdate) {
        try{
            return new ResponseEntity<>(taskService.updateTask(taskRequestUpdate)
                    , HttpStatus.OK);
        }
        catch(ObjectNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/task/{task_id}")
    @Operation(summary = "Delete Task", description = "Delete a task by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<String> deleteTaskById(
            @Parameter(description = "ID of the task to delete", required = true)
            @PathVariable Long task_id) {
        try{
            taskService.deleteTaskById(task_id);
            return new ResponseEntity<>(
                    "Task with ID: "+ task_id+ " is deleted successfully",
                    HttpStatus.NO_CONTENT
            );
        }
        catch(ObjectNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
