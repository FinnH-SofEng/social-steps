package com.socialsteps.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        user.setPassword(hash(user.getPassword()));
        users.add(user);
        userRepository.save(user);
        return user;
    }

    public User validateLogin(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        List<User> queriedUsers = getAllUsers();
        for (User u : queriedUsers){
            if (u.getUsername().equals(user.getUsername()) &&
            encoder.matches(user.getPassword(), u.getPassword())) {
            return u;
        }
        }
        return null;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    public String hash(String str){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(str);
    }
}
