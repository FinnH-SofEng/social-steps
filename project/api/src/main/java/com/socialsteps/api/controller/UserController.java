package com.socialsteps.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialsteps.api.command.Action;
import com.socialsteps.api.command.CreateAccount;
import com.socialsteps.api.model.User;
import com.socialsteps.api.model.Walk;
import com.socialsteps.api.repo.UserRepository;
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
    public Walk createAccount(@RequestBody User user) {
        this.command = new CreateAccount(this.userManager, user.getUsername(), user.getPassword());
        command.performAction();
        //placeholdser for error
        return new Walk();
    }
}
