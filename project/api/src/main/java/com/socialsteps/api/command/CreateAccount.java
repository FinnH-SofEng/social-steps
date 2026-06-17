package com.socialsteps.api.command;

import com.socialsteps.api.model.User;
import com.socialsteps.api.service.UserManager;

public class CreateAccount implements Action<User>{
    private UserManager receiver;
    private String username;
    private String password;

    public CreateAccount(UserManager receiver, String username, String password){
        this.receiver = receiver;
        this.username = username;
        this.password = password;
    }

    @Override
    public User performAction() {
        return receiver.addUser(new User(this.username, this.password));
    }
    
}
