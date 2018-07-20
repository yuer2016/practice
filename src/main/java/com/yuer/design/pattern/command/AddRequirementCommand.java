package com.yuer.design.pattern.command;

public class AddRequirementCommand extends ConcreteCommand {
    @Override
    public void execute() {
        super.requirementReceiver.doSomething();
    }
}
