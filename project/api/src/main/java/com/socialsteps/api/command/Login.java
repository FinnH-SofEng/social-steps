package com.socialsteps.api.command;

import com.socialsteps.api.model.User;
import com.socialsteps.api.service.UserManager;

public class Login implements Action<User>{
    private User user;
    private UserManager receiver;

    public Login(UserManager receiver, User user){
        this.user = user;
        this.receiver = receiver;
    }   

    @Override
    public User performAction() {
        return receiver.validateLogin(user);
    }
    
}
