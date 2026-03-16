package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Fisier {
    public static void main(String[] args) {
        File myObj = new File("C:\\Users\\Alex\\Desktop\\Pt paradigme lab\\Student proiect\\Student\\src\\main\\java\\Input.csv");
        List<String> linii = new ArrayList<>();

        // 1. Citirea datelor
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                linii.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit.");
            return;
        }

        // --- MODUL 1: Sortare după NUME (Primul cuvânt) ---
        linii.sort(Comparator.comparing(linie -> linie.split(" ")[0]));

        System.out.println("\n--- Sortat dupa NUME ---");
        linii.forEach(System.out::println);

        // --- MODUL 2: Sortare după FORMAȚIE/GRUPĂ (Ultimul cuvânt) ---
        linii.sort(Comparator.comparing(linie -> linie.split(" ")[3]));

        System.out.println("\n--- Sortat dupa FORMATIE (Grupa) ---");
        linii.forEach(System.out::println);

        linii.sort(Comparator.comparingInt(linie -> Integer.parseInt(linie.split(" ")[2])));

        System.out.println("\n--- Sortat dupa ANI (Crescator) ---");
        linii.forEach(System.out::println);
    }
}

