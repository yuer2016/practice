package com.yuer.tdd.solution.compression;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class GZipAllFiles {
    private static final int THREAD_COUNT = 4;

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);
        for (String fileName : args)
            findFiles(fileName).forEach(file -> service.execute(new GZipRunnable(file)));

        service.shutdown();
    }

    private static List<File> findFiles(String fileName) {
        List<File> result = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                Objects.requireNonNull(files);
                //Don't deal with composite files
                result = Arrays.stream(files)
                        .filter(f -> !f.isDirectory()).collect(Collectors.toList());
            } else {
                result.add(file);
            }
        }
        return result;
    }


}