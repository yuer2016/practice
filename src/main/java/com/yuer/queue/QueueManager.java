package com.yuer.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public enum Queue {
    INSTANCE;

    private Queue(){
    }

    private static final int QUEUE_SIZE = 100;
    private final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(QUEUE_SIZE);

    public void put(String value){
        blockingQueue.add(value);
    }

    public String get(){
        
        return blockingQueue.poll();
    }


}
