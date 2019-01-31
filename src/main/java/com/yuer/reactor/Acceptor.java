package com.yuer.reactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Acceptor implements Runnable {
    private int port; // server socket port
    private Selector selector;

    // 代表 server socket，通过LinkedBlockingQueue来模拟外部输入请求队列
    private BlockingQueue<InputSource> sourceQueue = new LinkedBlockingQueue<>();

    Acceptor(Selector selector, int port) {
        this.selector = selector;
        this.port = port;
    }

    //外部有输入请求后，需要加入到请求队列中
    public void addNewConnection(InputSource source) {
        sourceQueue.offer(source);
    }

    public int getPort() {
        return this.port;
    }

    public void run() {
        while (true) {
            InputSource source = null;
            try {
                source = sourceQueue.take();
            } catch (InterruptedException ignored) {
            }
            //接收到InputSource后将接收到event设置type为ACCEPT，并将source赋值给event
            if (source != null) {
                Event acceptEvent = new Event();
                acceptEvent.setSource(source);
                acceptEvent.setType(EventType.ACCEPT);
                selector.addEvent(acceptEvent);
            }
        }
    }
}
