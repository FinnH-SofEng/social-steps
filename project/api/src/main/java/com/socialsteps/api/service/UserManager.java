package com.socialsteps.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.socialsteps.api.model.User;
import com.socialsteps.api.repo.UserRepository;

@Service
public class UserManager {
    private List<User> users;
    private final UserRepository userRepository;
    public UserManager(UserRepository userRepository){
        this.userRepository = userRepository;
        this.users = this.getAllUsers();
    }
   
    public User addUser(User user){
        users.add(user);
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
}
