package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Implements implements Runnable{
    private int  nota;


    public Implements(int Nota) {
        this.nota = nota;

    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(nota))) {
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

