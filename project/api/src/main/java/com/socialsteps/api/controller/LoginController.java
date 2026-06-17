package com.socialsteps.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialsteps.api.command.Action;
import com.socialsteps.api.model.User;
import com.socialsteps.api.repo.WalkRepository;
import com.socialsteps.api.service.UserManager;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class LoginController extends Controller<User>{
    private final UserManager userManager;
    private Action<User> command;

    public LoginController(UserManager userManager) {
        this.userManager = userManager;
    }

}
