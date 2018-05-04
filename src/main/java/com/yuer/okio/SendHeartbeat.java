package com.yuer.okio;


public class SendHeartbeat implements Runnable {
    private final byte[] heartbeatMessage;
    private final ClientSocket clientSocket;

    SendHeartbeat(ClientSocket clientSocket, byte[] heartbeatMessage) {
        this.heartbeatMessage = heartbeatMessage;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        clientSocket.sendMessage(heartbeatMessage);
    }
}
