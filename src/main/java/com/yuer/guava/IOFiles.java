package com.yuer.guava;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class IOFiles {
    public List<String> getText(){
        List<String> lines = null;
        String filePath = ClassLoader
                .getSystemResource("changelog.txt").getFile();
        CharSource byteSource = Files.asCharSource(new File(filePath), Charsets.UTF_8);
        try {
            lines = byteSource.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
