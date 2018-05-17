package com.yuer.tdd.solution.compression;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GZipRunnable implements Runnable {
    private File file;

    public GZipRunnable(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        if (!file.getName().endsWith(".gz")) {
            File outputFile = new File(this.file.getParent(), this.file.getName() + ".gz");
            if (!outputFile.exists()) {

                try (InputStream inputStream =
                             new BufferedInputStream(new FileInputStream(file));
                     OutputStream outputStream =
                             new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(outputFile)))) {
                    int bytes;
                    while ((bytes = inputStream.read()) != -1) {
                        outputStream.write(bytes);
                    }
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
