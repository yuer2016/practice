package com.yuer.okio;


import okio.*;

import java.io.IOException;
import java.net.Socket;

import java.util.Objects;

public class ClientSocket implements Service {
    private Socket socket = null;
    private final ConfigFactory configFactory = new ConfigFactory();
    private volatile boolean isConnect = false;

    @Override
    public void connect() {
        if (socket != null) {
            closeConnect();
        }
        String ipKey = "ip";
        String portKey = "port";
        String ipStr = configFactory.getPropertiesByKey(ipKey);
        String portStr = configFactory.getPropertiesByKey(portKey);
        Objects.requireNonNull(ipStr);
        Objects.requireNonNull(portStr);
        int port = Integer.parseInt(portStr);
        try {
            socket = new Socket(ipStr, port);
            socket.setKeepAlive(true);
            if (socket.isConnected()) {
                isConnect = true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                Thread.sleep(100);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void revMessage() {
        try {
            BufferedSource sourceBuffer ;
            while (true) {
                if (isConnect){
                    sourceBuffer = Okio.buffer(Okio.source(socket));
                    Buffer buffer = new Buffer();
                    try {
                        int readSize = 1023;
                        long read = sourceBuffer.read(buffer, readSize);
                        if (read != -1) {
                            ByteString byteString = buffer.readByteString();
                            System.out.println(byteString.utf8());
                        }
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void closeConnect() {
        if (socket != null) {
            try {
                socket.close();
                socket = null;
                isConnect = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public synchronized void sendMessage(byte[] message) {
        try {
            if (isConnect){
                BufferedSink sinkBuffer = Okio.buffer(Okio.sink(socket));
                sinkBuffer.write(message);
                sinkBuffer.flush();
            }else {
                connect();
            }

        } catch (IOException e) {
            connect();
        }
    }
}
