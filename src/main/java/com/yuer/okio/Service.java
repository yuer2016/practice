package com.yuer.okio;

public interface Service {
    void connect();
    void revMessage();
    void sendMessage(byte[] message);
    void closeConnect();
}
