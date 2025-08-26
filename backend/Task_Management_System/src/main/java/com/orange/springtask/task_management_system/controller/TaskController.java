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
@RequestMapping("/tasks")
@Tag(name = "Task Management", description = "Endpoints for managing tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "Get Tasks", description = "Retrieve all tasks, optionally filtered by status.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Tasks not found")
    })
    public ResponseEntity<List<TaskResponse>> getTasks(
            @Parameter(description = "Status to filter tasks", required = false)
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "title", required = false) String title) {
        try{
            return new ResponseEntity<>(
                    taskService.getAllTasks(status, title),
                    HttpStatus.OK
            );
        }
        catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{task_id}")
    @Operation(summary = "Get Tasks", description = "Retrieve all tasks, optionally filtered by status.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Tasks not found")
    })
    public ResponseEntity<TaskResponse> getTasks(@PathVariable Long task_id) {
        try{
            return new ResponseEntity<>(
                    taskService.getTaskById(task_id),
                    HttpStatus.OK
            );
        }
        catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
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

    @PutMapping("/{task_id}")
    @Operation(summary = "Update Task", description = "Update an existing task.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Task updated successfully"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long task_id, @RequestBody TaskRequestUpdate taskRequestUpdate) {
        try{
            return new ResponseEntity<>(taskService.updateTask(task_id, taskRequestUpdate)
                    , HttpStatus.OK);
        }
        catch(ObjectNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{task_id}")
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
