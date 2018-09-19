package com.yuer.tdd.solution.search;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.*;

public class MockTest {
    @Test
    void mockTest() {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);
        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");
        // the following prints "first"
        assertThat(mockedList.get(0)).isEqualTo("first");
        // the following prints "null" because get(999) was not stubbed
        assertThat(mockedList.get(999)).isNull();
    }
}
