package com.yuer.guava;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class IOFilesTest {
    @Test
    void getText() {
        IOFiles ioFiles = new IOFiles();
        List<String> text = ioFiles.getText();
        assertThat(text.size()).isGreaterThan(0);
    }
}