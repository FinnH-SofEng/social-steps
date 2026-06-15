package com.socialsteps.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.socialsteps.api.model.User;

@Service
public class UserManager {
    private Map<Long, User> users;

    public UserManager(){
        this.users = new HashMap<>();
    }
    public UserManager(Map<Long, User> users){
        this.users = users;
    }

    public void addUser(User user){
        users.put(user.getId(), user);
    }
    
}
