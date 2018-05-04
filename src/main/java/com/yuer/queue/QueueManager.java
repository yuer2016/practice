package com.yuer.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public enum QueueManager {
    INSTANCE;

    QueueManager() {
    }
    private static final int QUEUE_SIZE = 100;
    private final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);

    public void put(String value) {
        try {
            blockingQueue.put(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String get() throws InterruptedException {

        return blockingQueue.take();
    }

    public int getQueueSize(){
        return blockingQueue.size();
    }


}
