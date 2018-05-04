package com.yuer.okio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SocketRun {

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        final byte[] heartBody = "zhanglei".getBytes();
        ClientSocket clientSocket = new ClientSocket();
        clientSocket.connect();
        SendHeartbeat sendHeartbeat = new SendHeartbeat(clientSocket, heartBody);
        scheduledExecutorService.scheduleWithFixedDelay(sendHeartbeat,5,5,TimeUnit.SECONDS);
        executorService.submit(clientSocket::revMessage);
    }

}


