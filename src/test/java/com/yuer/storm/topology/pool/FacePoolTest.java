package com.yuer.storm.topology.pool;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;


class FacePoolTest {

    @Test
    void execute() {
        IntStream.range(1,13).forEach(i ->{
            String result = FacePool.execute("c:/facepic/face64/1"+i+".jpg");
            System.out.println(result);
        });

    }
}