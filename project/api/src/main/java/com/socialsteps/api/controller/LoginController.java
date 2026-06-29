package com.socialsteps.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.socialsteps.api.command.Action;
import com.socialsteps.api.command.Login;
import com.socialsteps.api.model.User;
import com.socialsteps.api.service.UserManager;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/login")
public class LoginController extends Controller<User>{
    private final UserManager userManager;
    private Action<User> command;

    public LoginController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping
    public User login(@RequestBody User user){
        this.command = new Login(userManager, user);

        User loggedInUser = command.performAction();

        if (loggedInUser == null) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Invalid username or password"
            );
        }

        return loggedInUser;
    }
}


