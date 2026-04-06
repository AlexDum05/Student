package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hash {

    public static void main(String[] args) {

        File file = new File("C:/Users/Alex/Desktop/Pt paradigme lab/Student proiect/Student/src/main/java/org/example/Studenti.txt");

        Map<Student, Double> catalog = new HashMap<>();

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String linie = sc.nextLine();
                if (linie.trim().isEmpty()) continue;


                String[] parti = linie.split("\\s+");



                String prenume = parti[0];
                String nume = parti[1];
                int formatie = Integer.parseInt(parti[2]);
                int matricol = Integer.parseInt(parti[3]);

                // --- TRATARE NOTĂ ---
                String notaText = parti[4].replace(',', '.');
                double nota = Double.parseDouble(notaText);

                Student s = new Student(matricol, prenume, nume, formatie);
                catalog.put(s, nota);
            }

            // --- TESTARE AFISARE ---
            System.out.println("Catalogul complet:");
            for (Map.Entry<Student, Double> entry : catalog.entrySet()) {
                System.out.println("Student: " + entry.getKey().getPrenume() + " " + entry.getKey().getNume() +
                        " | Nota: " + entry.getValue());
            }

            // --- TESTARE CĂUTARE O(1) ---
            int cautat = 3599;
            Student dummy = new Student(cautat, "", "", 0);
            if (catalog.containsKey(dummy)) {
                System.out.println("\nNota studentului "+ cautat + " este: " + catalog.get(dummy));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Nu am gasit fisierul!");
        } catch (Exception e) {
            System.out.println("Eroare la procesarea datelor: " + e.getMessage());
        }
    }
}
