package com.socialsteps.api.command;

import com.socialsteps.api.model.User;
import com.socialsteps.api.service.UserManager;

public class CreateAccount implements Action<User>{
    private UserManager receiver;
    private Long id;
    private String username;
    private String password;

    public CreateAccount(){

    }

    @Override
    public User performAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'performAction'");
    }
    
}
