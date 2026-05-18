package org.example;

public class FileManager {
    public void startReadingFiles() {
        // Define the paths
        String pathStudenti = "src/main/java/org/example/Studenti.txt";
        String pathClasaStudentii = "C:\\Users\\Alex\\Desktop\\Pt paradigme lab\\Student proiect\\Student\\src\\main\\java\\ClasaStudentii.txt";

        // Create the tasks
        FileReaderTask task1 = new FileReaderTask(pathStudenti, "Thread-Studenti");
        FileReaderTask task2 = new FileReaderTask(pathClasaStudentii, "Thread-Clasa");

        // Create the threads
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        System.out.println("Starting threads from FileManager...\n");

        // Start the threads
        thread1.start();
        thread2.start();
    }
}

