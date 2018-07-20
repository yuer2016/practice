package com.yuer.design.pattern.command;

public class Client {
    public static void main(String[] args) {
        Invoke invoke = new Invoke();
        Command command = new AddRequirementCommand();
        invoke.setCommand(command);
        invoke.action();
    }
}
