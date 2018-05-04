package com.yuer.queue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


@DisplayName("A special test case")
class QueueManagerTest {
    private final String VALUE = "value";

    @BeforeEach
    void init(){
        QueueManager.INSTANCE.put(VALUE);
    }

    @Test
    @DisplayName("test blockQueue put value ")
    void put() {
        assertThat(QueueManager.INSTANCE.getQueueSize())
                .as("check queue size")
                .isEqualTo(1);
    }

    @Test
    @DisplayName("test blockQueue get value")
    void get() throws InterruptedException {
        assertThat(QueueManager.INSTANCE.get())
                .as("queue value ").isEqualTo(VALUE);
    }
}