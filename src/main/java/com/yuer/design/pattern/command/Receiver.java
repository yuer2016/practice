package com.yuer.design.pattern.command;

/**
 * 命令模式-执行方
 * */
public interface Receiver {
    void find();
    void delete();
    void doSomething();
}
