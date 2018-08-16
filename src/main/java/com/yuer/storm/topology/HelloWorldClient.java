package com.yuer.storm.topology;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;


import java.util.concurrent.TimeUnit;

@Slf4j
public class HelloWorldClient {
   /* private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    *//** Construct client connecting to HelloWorld server at {@code host:port}. *//*
    public HelloWorldClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build());
    }

    *//** Construct client for accessing HelloWorld server using the existing channel. *//*
    HelloWorldClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    *//** Say hello to server. *//*
    public String greet(String name) {
        log.info("Will try to greet " + name + " ...");
        Helloworld.HelloRequest request = Helloworld.HelloRequest.newBuilder().setName(name).build();
        Helloworld.HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {0}", e.getStatus());
            return null;
        }
        log.info("Greeting: " + response.getMessage());
        return response.getMessage();
    }*/

}
