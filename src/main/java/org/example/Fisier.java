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
        // Sortează după Formație (index 3), apoi după Nume (index 0)
        linii = sorteazaDupaFormatieSiNume(linii);

        System.out.println("\n--- Sortare dupa Formatie si Nume--- ");
        linii.forEach(System.out::println);

        // --- 3:Sortare dupa Ani ---
        linii.sort(Comparator.comparingInt(linie -> Integer.parseInt(linie.split(" ")[2])));

        System.out.println("\n--- Sortat dupa ANI (Crescator) ---");
        linii.forEach(System.out::println);

    }

    public static List<String> sorteazaDupaFormatieSiNume(List<String> linii) {
        linii.sort(Comparator.comparing((String s) -> s.split(" ")[3])
                .thenComparing(s -> s.split(" ")[0]));
        return linii;
    }
}

