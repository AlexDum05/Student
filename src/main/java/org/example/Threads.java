package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.example.StudentCuNota.citesteDinFisier;


public class Threads extends StudentCuNota{
    public static void main(String[] args) {
        // 1. Inițializăm lista completă de studenți
        List<StudentCuNota> listaInitiala = citesteDinFisier("src\\main\\java\\StudentcuNota.txt");

        // 2. Creăm Thread-ul 1 pentru note < 5
        Thread t1 = new Thread(() -> {
            filtreazaSiScrie(, "studenti_picati.txt", true);
        });

        // 3. Creăm Thread-ul 2 pentru note >= 5
        Thread t2 = new Thread(() -> {
            filtreazaSiScrie(studenti, "studenti_promovati.txt", false);
        });

        // 4. Pornim ambele thread-uri în paralel
        t1.start();
        t2.start();

        // 5. Așteptăm ca ambele thread-uri să termine execuția
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.err.println("Eroare la așteptarea thread-urilor: " + e.getMessage());
        }

        System.out.println("Exportul paralel s-a finalizat cu succes!");
    }

    // Metoda sincronizată/securizată pentru filtrare și scriere
    private static void filtreazaSiScrie(List<StudentCuNota> studenti, String numeFisier, boolean sub5) {
        // Resursele sunt inițializate în try-with-resources pentru a se închide automat
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {

            for (StudentCuNota s : studenti) {
                boolean conditie = sub5 ? (s.getNota() < 5) : (s.getNota() >= 5);

                if (conditie) {
                    writer.write(s.toString());
                    writer.newLine();
                }
            }
            System.out.println("Thread-ul pentru " + numeFisier + " a finalizat scrierea.");

        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișierul " + numeFisier + ": " + e.getMessage());
        }
    }
}


