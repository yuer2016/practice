package com.yuer.design.pattern.command;

public class DeletePageCommand extends ConcreteCommand {
    @Override
    public void execute() {
        super.pageReceiver.delete();
    }
}
