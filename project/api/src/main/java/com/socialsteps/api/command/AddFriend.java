package com.socialsteps.api.command;

import com.socialsteps.api.model.User;


public class AddFriend implements Action<User>{
    private User receiver;
    private User friend;

    public AddFriend(User friend, User receiver){
        this.friend = friend;
        this.receiver = receiver;
    }
    @Override
    public User performAction() {
        return receiver.addFriend(friend);
    }
}
