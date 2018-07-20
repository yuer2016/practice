package com.yuer.design.pattern.command;

abstract class ConcreteCommand implements Command {
     PageReceiver pageReceiver = new PageReceiver();
     RequirementReceiver requirementReceiver = new RequirementReceiver();
}
