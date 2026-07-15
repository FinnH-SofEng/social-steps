package com.socialsteps.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialsteps.api.command.Action;
import com.socialsteps.api.command.AddFriend;
import com.socialsteps.api.command.CreateAccount;
import com.socialsteps.api.dto.FriendInviteRequest;
import com.socialsteps.api.dto.UserResponse;
import com.socialsteps.api.model.Notification;
import com.socialsteps.api.model.User;
import com.socialsteps.api.service.UserManager;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController extends Controller<User>{

    private UserManager userManager;
    private Action<User> command;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping
    public User createAccount(@RequestBody User user) {
        this.command = new CreateAccount(this.userManager, user.getUsername(), user.getPassword());
        return command.performAction();
    }

    @PostMapping("/invite")
    public void sendInvite(@RequestBody FriendInviteRequest request) {
        userManager.sendInvite(request.senderId(), request.recipientUsername());
        System.out.println("6767");
    }

    @GetMapping("/notifications/{id}")
    public List<Notification> getNotificationsById(@PathVariable String id){
        return userManager.getNotificationsById(Long.parseLong(id));
    }

    @GetMapping("/friends/{id}")
    public List<UserResponse> getFriendsById(@PathVariable String id){
        List<UserResponse> userresponses = userManager.getFriendsById(Long.parseLong(id))
        .stream()
        .map(user -> new UserResponse(user.getId(), user.getUsername()))
        .toList();
        
        return userresponses;
    }
    
}
