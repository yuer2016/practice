package com.yuer.reactor;

public class Server {
    private Selector selector = new Selector();
    private Dispatcher eventLooper = new Dispatcher(selector);
    private Acceptor acceptor;

    Server(int port) {
        acceptor = new Acceptor(selector, port);
        acceptor.addNewConnection(new InputSource());
    }

    public void start() {
        eventLooper.registerEventHandler(EventType.ACCEPT, new AcceptEventHandler(selector));
        new Thread(acceptor, "Acceptor-" + acceptor.getPort()).start();
        eventLooper.handleEvents();
    }

}
