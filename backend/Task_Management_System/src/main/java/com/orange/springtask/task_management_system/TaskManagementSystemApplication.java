package com.orange.springtask.task_management_system;

import com.orange.springtask.task_management_system.entities.User;
import com.orange.springtask.task_management_system.repository.TaskRepository;
import com.orange.springtask.task_management_system.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;

@SpringBootApplication
public class TaskManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner populateDatabase(TaskRepository taskRepository,
                                              UserRepository userRepository) {
        return (args) -> {
//            User user = new User("seifalla",
//                    "semaziz2003@gmail.com",
//                    21,
//                    "password");
//            System.out.println(userRepository.save(user).toString());
        };
    }
}
