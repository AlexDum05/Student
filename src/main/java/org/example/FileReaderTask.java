package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTask implements Runnable {
    private String filePath;
    private String threadName;

    public FileReaderTask(String filePath, String threadName) {
        this.filePath = filePath;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("[" + threadName + "] " + line);
                Thread.sleep(10); // Small pause to mix the output
            }
        } catch (IOException e) {
            System.err.println("[" + threadName + "] Error reading file: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("[" + threadName + "] Thread was interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}
