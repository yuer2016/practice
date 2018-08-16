package com.yuer.storm.topology.pool;

import com.yuer.storm.topology.FaceServiceGrpc;
import com.yuer.storm.topology.Tensorflow;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

class SingleClient {
    private final ManagedChannel channel; //一个gRPC信道

    SingleClient(String host, int port) {
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
    }

    void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    String recognition(String imagePath){
        //阻塞/同步
        FaceServiceGrpc.FaceServiceBlockingStub faceBlockingStub = FaceServiceGrpc.newBlockingStub(channel);
        Tensorflow.FaceRequest faceRequest = Tensorflow.FaceRequest
                .newBuilder()
                .setImgPath(imagePath)
                .build();
        Tensorflow.FaceReply faceReply = faceBlockingStub.recognition(faceRequest);
        return faceReply.getDeviceId();
    }
}
