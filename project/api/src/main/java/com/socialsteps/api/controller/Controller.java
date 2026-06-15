package com.socialsteps.api.controller;

import com.socialsteps.api.command.Action;

public abstract class Controller<E> {
    private Action<E> command;
    public void setCommand(Action<E> command){
        this.command = command;
    }
}
