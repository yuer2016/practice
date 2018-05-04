package com.yuer.queue;

import static java.lang.Thread.*;

public class QueueManagerRun {
    public static void main(String[] args) {
        Thread production = new Thread(() -> {
            for (int i = 0;i< 10;i++){
                QueueManager.INSTANCE.put(i+"");
            }

        });

        Thread consumers = new Thread(() -> {
            while (true){
                try {
                    System.out.println(QueueManager.INSTANCE.get());
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });

        production.start();
        consumers.start();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumers.interrupt();

    }
}
