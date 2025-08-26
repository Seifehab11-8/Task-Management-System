package com.orange.springtask.task_management_system.service;

import com.orange.springtask.task_management_system.entities.User;
import com.orange.springtask.task_management_system.repository.UserRepository;
import com.orange.springtask.task_management_system.service.dto.principal.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null) throw new UsernameNotFoundException("username not found");
        return new UserPrincipal(user);
    }


}
